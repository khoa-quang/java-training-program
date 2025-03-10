package com.endava.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ToString
@Getter
@AllArgsConstructor
public class WarehouseDTO {

    private Long warehouseId;

    @NotBlank(message = "Location must not be blank")
    @Size(max = 100, message = "Location cannot exceed 100 characters")
    private String location;
}
