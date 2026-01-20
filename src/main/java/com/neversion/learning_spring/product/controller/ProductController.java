package com.neversion.learning_spring.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neversion.learning_spring.product.model.Product;
import com.neversion.learning_spring.product.persistence.ProductRepository;
import com.neversion.learning_spring.exceptions.ResourceNotFoundException;
import com.neversion.learning_spring.product.dto.ProductRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        Product newProduct = new Product();
        newProduct.setName(productRequest.getName());
        newProduct.setPrice(productRequest.getPrice());
        Product savedProduct = productRepository.save(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }
}
