package com.api.products.productapi.controller;

import com.api.products.productapi.exception.ProductAlreadyExistsException;
import com.api.products.productapi.exception.ProductNotFoundException;
import com.api.products.productapi.model.Product;
import com.api.products.productapi.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductsController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product){
        try{
            Product saveProduct = productService.saveProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveProduct);
        } catch (ProductAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    // Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products); // code 200
    }
    //retrieve by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        try{
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        }
        catch (ProductNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    // Retrieve products by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String category){
        List<Product> products = productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }

    // Update a product
    @PutMapping("{/id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product updateProduct){
        try{
            Product product = productService.updateProduct(id,updateProduct);
            return ResponseEntity.ok(product);
        }
        catch (ProductNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
