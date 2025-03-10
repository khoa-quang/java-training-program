package com.endava.catalog.controller;

import com.endava.catalog.dto.ProductWarehouseDTO;
import com.endava.catalog.service.ProductWarehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product-warehouse")
public class ProductWarehouseController {

    private final ProductWarehouseService pwService;

    @PostMapping
    public ResponseEntity<ProductWarehouseDTO> createOrUpdate(@RequestBody @Valid ProductWarehouseDTO dto) {
        log.info("Creating/Updating ProductWarehouse record: {}", dto);
        ProductWarehouseDTO saved = pwService.saveRecord(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<ProductWarehouseDTO>> getAll() {
        log.info("Fetching all ProductWarehouse records");
        List<ProductWarehouseDTO> list = pwService.getAllRecords();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{productId}/{warehouseId}")
    public ResponseEntity<ProductWarehouseDTO> getOne(
            @PathVariable Long productId,
            @PathVariable Long warehouseId) {

        log.info("Fetching ProductWarehouse record for productId={}, warehouseId={}",
                productId, warehouseId);
        ProductWarehouseDTO dto = pwService.getRecord(productId, warehouseId);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{productId}/{warehouseId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long productId,
            @PathVariable Long warehouseId) {

        log.info("Deleting ProductWarehouse record for productId={}, warehouseId={}",
                productId, warehouseId);
        pwService.deleteRecord(productId, warehouseId);
        return ResponseEntity.noContent().build();
    }
}
