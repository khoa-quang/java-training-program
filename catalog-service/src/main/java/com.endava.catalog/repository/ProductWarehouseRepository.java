package com.endava.catalog.repository;

import com.endava.catalog.model.ProductWarehouse;
import com.endava.catalog.model.ProductWarehouseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductWarehouseRepository extends JpaRepository<ProductWarehouse, ProductWarehouseId> {
}
