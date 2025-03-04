package com.endava.catalog.controller;

import com.endava.catalog.dto.WarehouseDTO;
import com.endava.catalog.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<WarehouseDTO> create(@RequestBody @Valid WarehouseDTO dto) {
        log.info("Creating Warehouse: {}", dto);
        WarehouseDTO created = warehouseService.createWarehouse(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<WarehouseDTO>> getAll() {
        log.info("Fetching all warehouses");
        List<WarehouseDTO> warehouses = warehouseService.getAllWarehouses();
        return ResponseEntity.ok(warehouses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseDTO> getById(@PathVariable Long id) {
        log.info("Fetching warehouse by id: {}", id);
        WarehouseDTO warehouse = warehouseService.getWarehouseById(id);
        return ResponseEntity.ok(warehouse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarehouseDTO> update(@PathVariable Long id,
                                               @RequestBody @Valid WarehouseDTO dto) {
        log.info("Updating warehouse with id {}: {}", id, dto);
        WarehouseDTO updated = warehouseService.updateWarehouse(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Deleting warehouse with id: {}", id);
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.noContent().build();
    }
}
