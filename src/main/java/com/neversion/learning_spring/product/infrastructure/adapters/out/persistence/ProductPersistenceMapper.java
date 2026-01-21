package com.neversion.learning_spring.product.infrastructure.adapters.out.persistence;

import com.neversion.learning_spring.product.domain.model.Product;

import org.springframework.stereotype.Component;

@Component
public class ProductPersistenceMapper {

    public Product toDomain(ProductEntity productEntity) {
        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice(),
                productEntity.isActive());
    }

    public ProductEntity toEntity(Product product) {
        return new ProductEntity(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.isActive());
    }
}
