package com.scaler.firstspringapi.controllers;

import com.scaler.firstspringapi.models.Product;
import com.scaler.firstspringapi.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    ProductController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping({"/{id}"})
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }
}
