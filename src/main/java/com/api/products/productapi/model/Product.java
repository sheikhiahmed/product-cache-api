package com.api.products.productapi.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Product {

   @Id
    private Long id;

    @NotBlank(message ="Name is mandatory")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;

    private String description;

    @NotBlank(message = "Price is mandatory")
    @Min(value = 0, message = "Price must be a positive value")
    private Double price;

    @NotBlank(message = "Category is mandatory")
    private String category;


}