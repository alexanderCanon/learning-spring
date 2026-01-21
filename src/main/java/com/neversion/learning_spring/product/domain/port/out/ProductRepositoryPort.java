package com.neversion.learning_spring.product.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.neversion.learning_spring.product.domain.model.Product;

public interface ProductRepositoryPort {

    Product save(Product product);

    Optional<Product> getById(Long id);

    List<Product> getAll();

    Optional<Product> update(Long id, Product product);

    void delete(Long id);
}

// This interface defines the contract for persistent storage and retrieval of
// products
