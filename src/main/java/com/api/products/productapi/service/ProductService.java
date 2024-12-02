package com.api.products.productapi.service;

import com.api.products.productapi.dto.ProductDTO;
import com.api.products.productapi.exception.ProductAlreadyExistsException;
import com.api.products.productapi.exception.ProductNotFoundException;
import com.api.products.productapi.model.Product;
import com.api.products.productapi.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CacheManager cacheManager;

    public ProductService(ProductRepository productRepository, CacheManager cacheManager){
        this.productRepository = productRepository;
        this.cacheManager = cacheManager;
    }


    //save a product using ProductDTO
    @CachePut(value = "products", key = "#result.id")
    public ProductDTO saveProduct(ProductDTO productDTO ) throws ProductAlreadyExistsException {
        // Convert DTO to Entity
        Product product = productDTO.toEntity();

     /*   if(productRepository.existsById(product.getId())){
            throw new ProductAlreadyExistsException("Product with ID"+ product.getId()+"already exists");
        }*/
        //Save the entity and return the saved DTO
        Product saveProduct = productRepository.save(product);

                ProductDTO p =ProductDTO.fromEntity(saveProduct);
                return p;

    }

    // get all products
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductDTO::fromEntity) // Convert Product entities to ProductDTOs
                .collect(Collectors.toList());
    }


    //retrieve one product
    @Cacheable(value = "products", key = "#id")
    public ProductDTO getProductById(Long id) throws ProductNotFoundException {
        Product product = productRepository
                .findById(id)
                .orElseThrow(()->new ProductNotFoundException("Product with ID " + id + " not found."));
        return ProductDTO.fromEntity(product);
    }

    // Retrieve product by category
    public List<ProductDTO> getProductsByCategory(String category){
        return productRepository.findByCategory(category).stream()
                .map(ProductDTO::fromEntity)  // Convert Product entities to ProductDTOs
                .collect(Collectors.toList());
    }


    @CachePut(value = "products", key = "#product.id")
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) throws ProductNotFoundException {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found."));

        // Update entity fields with the DTO data
        existingProduct.setName(productDTO.getName());
        existingProduct.setCategory(productDTO.getCategory());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setDescription(productDTO.getDescription());

        Product updatedProduct = productRepository.save(existingProduct);
        return ProductDTO.fromEntity(updatedProduct);  // Convert updated entity back to DTO
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
