package com.scaler.firstspringapi.services;

import com.scaler.firstspringapi.dtos.FakeStoreProductDto;
import com.scaler.firstspringapi.exceptions.ProductNotFoundException;
import com.scaler.firstspringapi.models.Product;
import com.scaler.firstspringapi.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;

    SelfProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product createProduct(FakeStoreProductDto fakeStoreProductDto) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }
}
