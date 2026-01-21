package com.neversion.learning_spring.product.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.neversion.learning_spring.exceptions.ResourceNotFoundException;
import com.neversion.learning_spring.product.application.port.in.GetProductUseCase;
import com.neversion.learning_spring.product.domain.model.Product;
import com.neversion.learning_spring.product.domain.port.out.ProductRepositoryPort;

@Service
public class GetProductService implements GetProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public GetProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepositoryPort.getAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepositoryPort.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }
}
