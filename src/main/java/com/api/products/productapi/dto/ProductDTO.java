package com.api.products.productapi.dto;

import com.api.products.productapi.model.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private Long id;

    @NotNull(message = "Name is mandatory")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;

    private String description;

    @Min(value = 0, message = "Price must be a positive value")
    private double price;

    @NotNull(message = "Category is mandatory")
    private String category;

    //Conversion from DTO to Entity
    public Product toEntity(){
        return Product.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .price(this.price)
                .category(this.category)
                .build();

    }

    //Conversion from Entity to DTO
    public static ProductDTO fromEntity(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .build();
    }

}
