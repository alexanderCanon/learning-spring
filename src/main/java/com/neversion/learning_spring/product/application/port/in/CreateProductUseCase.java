package com.neversion.learning_spring.product.application.port.in;

import com.neversion.learning_spring.product.domain.model.Product;

public interface CreateProductUseCase {
    Product createProduct(Product product);
}
