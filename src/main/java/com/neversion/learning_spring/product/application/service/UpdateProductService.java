package com.neversion.learning_spring.product.application.service;

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
    public Product updateProduct(Long id) {
        Product existingProduct = productRepositoryPort.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(existingProduct.getName());
            existingProduct.setPrice(existingProduct.getPrice());
            return productRepositoryPort.update(id);
        }
        return null;
    }
}
