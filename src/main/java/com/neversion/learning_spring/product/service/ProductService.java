package com.neversion.learning_spring.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.neversion.learning_spring.product.model.Product;
import com.neversion.learning_spring.product.persistence.ProductRepository;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}
