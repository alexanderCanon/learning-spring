package com.neversion.learning_spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neversion.learning_spring.model.Product;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private List<Product> products = new ArrayList<>();

    public ProductController() {
        products.add(new Product(1, "Product 1", 10.0));
        products.add(new Product(2, "Product 2", 20.0));
        products.add(new Product(3, "Product 3", 30.0));
    }

    @GetMapping
    public List<Product> getProducts() {
        return products;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        products.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
}
