package com.api.products.productapi.service;

import com.api.products.productapi.model.Product;
import com.api.products.productapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public Product saveProduct( Product product ){
        return productRepository.save(product);

    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
