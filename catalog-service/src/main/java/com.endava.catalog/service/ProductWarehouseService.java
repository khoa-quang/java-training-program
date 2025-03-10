package com.endava.catalog.service;

import com.endava.catalog.dto.ProductWarehouseDTO;
import com.endava.catalog.model.Product;
import com.endava.catalog.model.ProductWarehouse;
import com.endava.catalog.model.ProductWarehouseId;
import com.endava.catalog.model.Warehouse;
import com.endava.catalog.exception.ResourceNotFoundException;
import com.endava.catalog.repository.ProductRepository;
import com.endava.catalog.repository.ProductWarehouseRepository;
import com.endava.catalog.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductWarehouseService {

    private final ProductWarehouseRepository pwRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public ProductWarehouseDTO saveRecord(ProductWarehouseDTO dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found with id " + dto.getProductId()));
        Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Warehouse not found with id " + dto.getWarehouseId()));

        ProductWarehouseId pwId = new ProductWarehouseId(dto.getProductId(), dto.getWarehouseId());

        // If existing record, update quantity; otherwise create new.
        ProductWarehouse record = pwRepository.findById(pwId)
                .orElse(new ProductWarehouse(product, warehouse, dto.getQuantityInStock()));
        record.setQuantityInStock(dto.getQuantityInStock());

        ProductWarehouse saved = pwRepository.save(record);

        return toDTO(saved);
    }

    public List<ProductWarehouseDTO> getAllRecords() {
        return pwRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProductWarehouseDTO getRecord(Long productId, Long warehouseId) {
        ProductWarehouseId pwId = new ProductWarehouseId(productId, warehouseId);
        ProductWarehouse record = pwRepository.findById(pwId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No ProductWarehouse record found for productId "
                                + productId + " and warehouseId " + warehouseId));
        return toDTO(record);
    }

    public void deleteRecord(Long productId, Long warehouseId) {
        ProductWarehouseId pwId = new ProductWarehouseId(productId, warehouseId);
        if (!pwRepository.existsById(pwId)) {
            throw new ResourceNotFoundException("No ProductWarehouse record found for productId "
                    + productId + " and warehouseId " + warehouseId);
        }
        pwRepository.deleteById(pwId);
    }

    // Helper to convert entity -> DTO
    private ProductWarehouseDTO toDTO(ProductWarehouse record) {
        return new ProductWarehouseDTO(
                record.getId().getProductId(),
                record.getId().getWarehouseId(),
                record.getQuantityInStock()
        );
    }
}
