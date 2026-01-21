package com.neversion.learning_spring.product.application.service;

import com.neversion.learning_spring.exceptions.ResourceNotFoundException;
import com.neversion.learning_spring.product.application.port.in.UpdateProductUseCase;
import com.neversion.learning_spring.product.domain.port.out.ProductRepositoryPort;
import com.neversion.learning_spring.product.domain.model.Product;

import org.springframework.stereotype.Service;

@Service
public class UpdateProductService implements UpdateProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public UpdateProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Product updateProduct(Long id, Product product) {

        Product existingProduct = productRepositoryPort.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        Product updatedProduct = new Product(
                existingProduct.id(),
                product.name(),
                product.price(),
                existingProduct.active());
        return productRepositoryPort.save(updatedProduct);
    }
}
