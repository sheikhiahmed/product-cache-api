package com.api.products.productapi.service;

import com.api.products.productapi.exception.ProductAlreadyExistsException;
import com.api.products.productapi.exception.ProductNotFoundException;
import com.api.products.productapi.model.Product;
import com.api.products.productapi.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CacheManager cacheManager;

    public ProductService(ProductRepository productRepository, CacheManager cacheManager){
        this.productRepository = productRepository;
        this.cacheManager = cacheManager;
    }


    //save a product
    @CachePut(value = "products", key = "#product.id")
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
    @Cacheable(value = "products", key = "#id")
    public Product getProductById(Long id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product with ID " + id + " not found."));
    }

    // Retrieve product by category
    public List<Product> getProductsByCategory(String category){
        return productRepository.findByCategory(category);

    }

    @CachePut(value = "products", key = "#product.id")
    public Product updateProduct(Long id, Product updateProduct) throws ProductNotFoundException{
        Product existingProduct = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product not found"));
        existingProduct.setName(updateProduct.getName());
        existingProduct.setCategory(updateProduct.getCategory());
        existingProduct.setPrice(updateProduct.getPrice());
        productRepository.save(existingProduct);
        return existingProduct;

    }
    // Delete a product
    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(Long id) throws ProductNotFoundException {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }
        productRepository.deleteById(id);
    }
}
