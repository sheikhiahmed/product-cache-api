package com.api.products.productapi.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Product {

   @Id
    private Long id;

    @NotNull(message ="Name is mandatory")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;

    private String description;

    @Min(value = 0, message = "Price must be a positive value")
    private double price;

    @NotNull(message = "Category is mandatory")
    private String category;


}