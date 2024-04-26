package com.scaler.firstspringapi.services;

import com.scaler.firstspringapi.exceptions.NoProductsException;
import com.scaler.firstspringapi.exceptions.ProductAlreadyExistsException;
import com.scaler.firstspringapi.exceptions.ProductNotFoundException;
import com.scaler.firstspringapi.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts() throws NoProductsException;

    Product createProduct(Product product) throws ProductAlreadyExistsException;

    Product replaceProduct(Long id, Product product);

    Product updateProduct(Long id,Product product);

    void deleteProduct(Long id);
}
