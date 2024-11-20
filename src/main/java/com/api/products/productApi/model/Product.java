package com.api.products.productApi.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
public class Product {

   @Id
    private Long id;

    @NotBlank(message ="Name is mandatory")
    private String name;

    private String description;

    @NotBlank(message = "Price is mandatory")
    private Double price;

    @NotBlank(message = "Category is mandatory")
    private String category;


}