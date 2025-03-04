package com.endava.catalog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product_warehouse")
@Setter
@Getter
@NoArgsConstructor
public class ProductWarehouse {

    @EmbeddedId
    private ProductWarehouseId id;

    private int quantityInStock;

    public ProductWarehouse(Product product, Warehouse warehouse, int qty) {
        this.quantityInStock = qty;

        // Initialize the embedded ID
        this.id = new ProductWarehouseId(
                product != null ? product.getProductId() : null,
                warehouse != null ? warehouse.getWarehouseId() : null
        );
    }
}