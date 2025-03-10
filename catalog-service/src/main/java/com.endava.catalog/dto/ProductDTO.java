package com.endava.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@ToString
@Getter
@AllArgsConstructor
public class ProductDTO {

    private Long productId;

    @NotBlank(message = "Product name must not be blank")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @NotBlank
    @Size(max = 50, message = "Category cannot exceed 50 characters")
    private String category;
}
