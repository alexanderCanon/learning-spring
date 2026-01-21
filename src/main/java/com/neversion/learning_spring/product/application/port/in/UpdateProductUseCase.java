package com.neversion.learning_spring.product.application.port.in;

import com.neversion.learning_spring.product.domain.model.Product;

public interface UpdateProductUseCase {
    // Product updateProduct(Long id, Product product);
    Product updateProduct(Long id);
}
