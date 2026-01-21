package com.neversion.learning_spring.product.application.service;

import org.springframework.stereotype.Service;

import com.neversion.learning_spring.product.application.port.in.CreateProductUseCase;
import com.neversion.learning_spring.product.domain.model.Product;
import com.neversion.learning_spring.product.domain.port.out.ProductRepositoryPort;

@Service
public class CreateProductService implements CreateProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public CreateProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepositoryPort.save(product);
    }
}
