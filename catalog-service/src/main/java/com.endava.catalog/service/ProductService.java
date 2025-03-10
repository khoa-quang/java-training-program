package com.endava.catalog.service;

import com.endava.catalog.dto.ProductDTO;
import com.endava.catalog.model.Product;
import com.endava.catalog.exception.ResourceNotFoundException;
import com.endava.catalog.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDTO createProduct(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategory(dto.getCategory());

        Product saved = productRepository.save(product);
        return convertToDTO(saved);
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        return convertToDTO(product);
    }

    public ProductDTO updateProduct(Long id, ProductDTO updatedDTO) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        existing.setName(updatedDTO.getName());
        existing.setDescription(updatedDTO.getDescription());
        existing.setPrice(updatedDTO.getPrice());
        existing.setCategory(updatedDTO.getCategory());

        Product saved = productRepository.save(existing);
        return convertToDTO(saved);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
        productRepository.deleteById(id);
    }

    // Helper to convert entity -> DTO
    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory()
        );
    }
}
