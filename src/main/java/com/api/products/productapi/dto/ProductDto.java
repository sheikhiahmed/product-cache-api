package com.api.products.productapi.dto;

import com.api.products.productapi.model.Product;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Name is mandatory")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;

    private String description;

    @Min(value = 0, message = "Price must be a positive value")
    private double price;

    @NotNull(message = "Category is mandatory")
    private String category;
    public Product productBuild(){
        return Product.builder()
                .name(this.name)
                .description(this.description)
                .price(this.price)
                .category(this.category)
                .build();

    }

}
