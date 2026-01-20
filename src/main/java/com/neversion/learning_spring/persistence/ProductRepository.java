package com.neversion.learning_spring.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neversion.learning_spring.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
