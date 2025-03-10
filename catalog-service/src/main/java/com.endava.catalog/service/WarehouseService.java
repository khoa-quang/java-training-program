package com.endava.catalog.service;

import com.endava.catalog.dto.WarehouseDTO;
import com.endava.catalog.model.Warehouse;
import com.endava.catalog.exception.ResourceNotFoundException;
import com.endava.catalog.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseDTO createWarehouse(WarehouseDTO dto) {
        Warehouse entity = new Warehouse();
        entity.setLocation(dto.getLocation());

        Warehouse saved = warehouseRepository.save(entity);
        return convertToDTO(saved);
    }

    public List<WarehouseDTO> getAllWarehouses() {
        return warehouseRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public WarehouseDTO getWarehouseById(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found with id " + id));
        return convertToDTO(warehouse);
    }

    public WarehouseDTO updateWarehouse(Long id, WarehouseDTO dto) {
        Warehouse existing = warehouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found with id " + id));

        existing.setLocation(dto.getLocation());
        Warehouse saved = warehouseRepository.save(existing);
        return convertToDTO(saved);
    }

    public void deleteWarehouse(Long id) {
        if (!warehouseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Warehouse not found with id " + id);
        }
        warehouseRepository.deleteById(id);
    }

    // Helper to convert entity -> DTO
    private WarehouseDTO convertToDTO(Warehouse warehouse) {
        return new WarehouseDTO(warehouse.getWarehouseId(), warehouse.getLocation());
    }
}
