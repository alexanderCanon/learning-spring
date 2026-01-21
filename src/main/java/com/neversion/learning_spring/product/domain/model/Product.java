package com.neversion.learning_spring.product.domain.model;

public record Product(
        Long id,
        String name,
        Double price,
        boolean active) {
}
