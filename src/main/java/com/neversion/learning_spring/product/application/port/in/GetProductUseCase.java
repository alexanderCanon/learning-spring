package com.neversion.learning_spring.product.application.port.in;

import java.util.List;

import com.neversion.learning_spring.product.domain.model.Product;

public interface GetProductUseCase {
    Product getProduct(Long id);

    List<Product> getAllProducts();
}
