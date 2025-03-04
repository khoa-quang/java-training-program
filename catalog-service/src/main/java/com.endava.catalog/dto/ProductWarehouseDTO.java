package com.endava.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@AllArgsConstructor
public class ProductWarehouseDTO {

    @NotNull(message = "productId must be provided")
    private Long productId;

    @NotNull(message = "warehouseId must be provided")
    private Long warehouseId;

    @Min(value = 0, message = "quantityInStock cannot be negative")
    private int quantityInStock;
}
