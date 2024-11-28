package com.api.products.productapi.service;

import com.api.products.productapi.exception.ProductAlreadyExistsException;
import com.api.products.productapi.exception.ProductNotFoundException;
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
    public Product saveProduct( Product product ) throws ProductAlreadyExistsException {
        if(productRepository.existsById(product.getId())){
            throw new ProductAlreadyExistsException("Product with ID"+ product.getId()+"already exists");
        }
        return productRepository.save(product);

    }

    // get All the products of product
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    //retrieve one product
    public Product getProductById(Long id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product with ID " + id + " not found."));
    }

    // Retrieve product by category
    public List<Product> getProductsByCategory(String category){
        return productRepository.findByCategory(category);

    }

    public Product updateProduct(Long id, Product updateProduct) throws ProductNotFoundException{
        Product existingProduct = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product not found"));
        existingProduct.setName(updateProduct.getName());
        existingProduct.setCategory(updateProduct.getCategory());
        existingProduct.setPrice(updateProduct.getPrice());
        productRepository.save(existingProduct);
        return existingProduct;

    }
}
