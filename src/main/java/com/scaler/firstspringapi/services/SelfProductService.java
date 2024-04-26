package com.scaler.firstspringapi.services;

import com.scaler.firstspringapi.exceptions.NoProductsException;
import com.scaler.firstspringapi.exceptions.ProductAlreadyExistsException;
import com.scaler.firstspringapi.exceptions.ProductNotFoundException;
import com.scaler.firstspringapi.models.Category;
import com.scaler.firstspringapi.models.Product;
import com.scaler.firstspringapi.repositories.CategoryRepository;
import com.scaler.firstspringapi.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
@Primary
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {

            Optional<Product> optionalProduct = productRepository.findById(id);
            if(optionalProduct.isEmpty()){
                throw  new ProductNotFoundException(id, "Product not found ");
            }
            return optionalProduct.get();


    }

    @Override
    public List<Product> getAllProducts() throws NoProductsException {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()){
            throw  new NoProductsException("No products in DB");
        }
        return products;
    }

    @Override
    public Product createProduct(Product product) throws ProductAlreadyExistsException {
        //check if product with title already exists
        List<Product> products = productRepository.findByTitle(product.getTitle());
        if(!products.isEmpty()){
            throw new ProductAlreadyExistsException("Product with title "+product.getTitle()+" Already exists");
        }
        //Before saving the Product object in the DB, save the category object.
        Category category = product.getCategory();
        if (category.getId() == null) {
            //we need to save the category
            Category savedCategory = categoryRepository.save(category);
            product.setCategory(savedCategory);
        } else {
            //we should check if the category id is valid or not.
            System.out.println("line 54 : to be implemented");
        }

        Product savedProduct =  productRepository.save(product);
        Optional<Category> optionalCategory = categoryRepository.findById(savedProduct.getCategory().getId());
        if(optionalCategory.isEmpty()){
            throw  new ProductAlreadyExistsException( "Product not created ");
        }
        Category category1 = optionalCategory.get();
        savedProduct.setCategory(category1);
        return savedProduct;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }


}
