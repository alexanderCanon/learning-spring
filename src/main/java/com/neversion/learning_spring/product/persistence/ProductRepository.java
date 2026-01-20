package com.neversion.learning_spring.product.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neversion.learning_spring.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
