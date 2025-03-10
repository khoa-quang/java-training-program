package com.endava.catalog.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "warehouses")
@Setter
@Getter
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseId;

    @NotBlank(message = "Location must not be blank")
    @Size(max = 100, message = "Location cannot exceed 100 characters")
    private String location;
}
