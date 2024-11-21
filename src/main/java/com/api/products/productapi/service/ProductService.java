package com.api.products.productapi.service;

import com.api.products.productapi.model.Product;
import com.api.products.productapi.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    //save a product
    public Product saveProduct( Product product ){
        return productRepository.save(product);

    }

    // get All the products of product
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    //retrieve one product
    public Product getOneProduct(Long id){
        return productRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Entity is not found"));
    }

}
