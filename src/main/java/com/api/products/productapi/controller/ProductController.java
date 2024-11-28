package com.api.products.productapi.controller;

import com.api.products.productapi.model.Product;
import com.api.products.productapi.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product){
        Product saveProduct = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED) // code 201
                .body(saveProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products); // code 200
    }
    //retrieve by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id){
        try{
            Product product = productService.getOneProduct(id);
            return ResponseEntity.ok(product);
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    // retrieve Type
    @GetMapping("/category/{type}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String type){
        List<Product> products = productService.getProductsByCategory(type);
        return ResponseEntity.ok(products);
    }


}
