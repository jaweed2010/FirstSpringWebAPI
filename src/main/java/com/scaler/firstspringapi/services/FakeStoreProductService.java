package com.scaler.firstspringapi.services;

import com.scaler.firstspringapi.dtos.FakeStoreProductDto;
import com.scaler.firstspringapi.models.Category;
import com.scaler.firstspringapi.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{
    public RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    private Product convertFakeStoreDtoToProduct(FakeStoreProductDto dto){
        Product product = new Product();
        product.setId(dto.getId());
        product.setDesc(dto.getDesc());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setImage(dto.getImage());
        Category category = new Category();
        category.setDesc(dto.getCategory());
        product.setCategory(category);
        return  product;
    }
    @Override
    public Product getProductById(Long id) {
        //call fakestore API and return product details
       FakeStoreProductDto fakeStoreProductDto=
               restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDto.class);

       if (fakeStoreProductDto == null){
           return null;
       }
        return convertFakeStoreDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        //call fakestore API and return product details
        FakeStoreProductDto[] fakeStoreProductDtos ;
        fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreProductDto[].class);
        //convert from response to product object
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto f :fakeStoreProductDtos){
            products.add(convertFakeStoreDtoToProduct(f));
        }

        return products;
    }
}
