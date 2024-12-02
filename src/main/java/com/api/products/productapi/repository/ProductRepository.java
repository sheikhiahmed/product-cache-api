package com.api.products.productapi.repository;

import com.api.products.productapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Custom query.
    List<Product> findByCategory(String category);
}
