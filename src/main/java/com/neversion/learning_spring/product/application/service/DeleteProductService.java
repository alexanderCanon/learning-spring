package com.neversion.learning_spring.product.application.service;

import org.springframework.stereotype.Service;

import com.neversion.learning_spring.product.application.port.in.DeleteProductUseCase;
import com.neversion.learning_spring.product.domain.port.out.ProductRepositoryPort;

@Service
public class DeleteProductService implements DeleteProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public DeleteProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepositoryPort.delete(id);
    }
}
