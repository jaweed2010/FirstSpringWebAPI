package com.scaler.firstspringapi.controllers;

import com.scaler.firstspringapi.exceptions.NoProductsException;
import com.scaler.firstspringapi.exceptions.ProductAlreadyExistsException;
import com.scaler.firstspringapi.exceptions.ProductNotFoundException;
import com.scaler.firstspringapi.models.Product;
import com.scaler.firstspringapi.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    ProductController(@Qualifier("selfProductService") ProductService productService){
        this.productService=productService;
    }

    @GetMapping()
    public List<Product> getAllProducts() throws NoProductsException {
        return productService.getAllProducts();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
// TODO Request Body is different from Deppak's
    @PostMapping()

    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws ProductAlreadyExistsException {
        Product savedProduct =productService.createProduct( product);
        return new ResponseEntity<>(savedProduct,HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id,@RequestBody Product product){ return  productService.replaceProduct(id,product);}
}
// TODO
//createProduct
//deleteProduct
//updateProduct -> Partial Update (PATCH)
//replaceProduct -> Replace (PUT)

// TODO Exception handler here?