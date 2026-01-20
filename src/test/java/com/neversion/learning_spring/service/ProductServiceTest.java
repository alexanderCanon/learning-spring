package com.neversion.learning_spring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.neversion.learning_spring.persistence.ProductRepository;
import com.neversion.learning_spring.model.Product;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock // Crea un repositorio falso (Mock, simulacro)
    private ProductRepository productRepository;

    @InjectMocks // Crea una instancia del servicio real e inyecta el mock
    private ProductService productService;

    @Test
    void shouldCreateProduct() {
        // ARRANGE
        Product product = new Product(1L, "Laptop", 1000.0);
        Product savedProduct = new Product(1L, "Laptop", 1000.0);

        // ACT
        when(productRepository.save(product)).thenReturn(savedProduct);
        Product result = productService.createProduct(product);

        // ASSERT
        assertEquals("Laptop", result.getName());

        verify(productRepository).save(product);
    }

    // @Test
    // void shouldGetAllProducts() {

    // }

    // @Test
    // void shouldGetProductById() {

    // }
}
