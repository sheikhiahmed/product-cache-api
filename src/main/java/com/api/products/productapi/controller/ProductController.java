package com.api.products.productapi.controller;

import com.api.products.productapi.model.Product;
import com.api.products.productapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product){
        Product saveProduct = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED) // code 201
                .body(saveProduct);

    }

}
