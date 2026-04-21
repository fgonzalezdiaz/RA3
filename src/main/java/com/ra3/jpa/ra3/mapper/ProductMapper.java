package com.ra3.jpa.ra3.mapper;

import org.springframework.stereotype.Component;

import com.ra3.jpa.ra3.dto.ProductRequestDto;
import com.ra3.jpa.ra3.dto.ProductResponseDto;
import com.ra3.jpa.ra3.model.Product;

/**
 * Mapper per convertir entre l'entitat Product i els DTOs.
 */
@Component
public class ProductMapper {

    public ProductResponseDto toResponseDto(Product product) {
        return new ProductResponseDto(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getStock(),
            product.getPrice(),
            product.getRating(),
            product.getCondition()
        );
    }

    public Product toEntity(ProductRequestDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setStock(dto.getStock());
        product.setPrice(dto.getPrice());
        product.setRating(dto.getRating());
        product.setCondition(dto.getCondition());
        return product;
    }
}
