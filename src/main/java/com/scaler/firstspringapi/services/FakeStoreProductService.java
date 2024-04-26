package com.scaler.firstspringapi.services;

import com.scaler.firstspringapi.dtos.FakeStoreProductDto;
import com.scaler.firstspringapi.exceptions.ProductAlreadyExistsException;
import com.scaler.firstspringapi.exceptions.ProductNotFoundException;
import com.scaler.firstspringapi.models.Category;
import com.scaler.firstspringapi.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    public RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    private Product convertFakeStoreDtoToProduct(FakeStoreProductDto dto){
        Product product = new Product();

        product.setId(dto.getId());
        product.setDescription(dto.getDescription());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setImage(dto.getImage());
        Category category = new Category();
        category.setTitle(dto.getCategory());
        product.setCategory(category);
        return  product;
    }
    private  FakeStoreProductDto convertProductToFakeStoreDto(Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImage());
        fakeStoreProductDto.setCategory(product.getCategory().getTitle());
        return fakeStoreProductDto;
    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        //call fakestore API and return product details
       FakeStoreProductDto fakeStoreProductDto=
               restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDto.class);

       if (fakeStoreProductDto == null){
           throw new ProductNotFoundException(id,"Product with id "+ id+" not found");
       }
        return convertFakeStoreDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        //call fakestore API and return product details
        FakeStoreProductDto[] fakeStoreProductDtos =
                restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreProductDto[].class);
        //convert from response to product object
        List<Product> products = new ArrayList<>();
        if (fakeStoreProductDtos != null) {
            for (FakeStoreProductDto f :fakeStoreProductDtos){
                products.add(convertFakeStoreDtoToProduct(f));
            }
            return  products;
        }

        return null;
    }

    public Product updateProduct(Long id, Product product) {
        return null;
    }
   // TODO complete create Product
    @Override
    public Product createProduct(Product product) {

        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        //convert to fackeStore Dto from Product
        FakeStoreProductDto fakeStoreProductDto = convertProductToFakeStoreDto(product);
        //call fakestore and get fakestore Dto
        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDto.class,
                        restTemplate.getMessageConverters());
        FakeStoreProductDto response =
                restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        //convert to Product
        // TODO returning null below which should not be the case
        if (response != null) {
            return convertFakeStoreDtoToProduct(response);
        }
        return  null;
    }
    public void deleteProduct(Long id){

    }
}
