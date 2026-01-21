package com.neversion.learning_spring.product.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.neversion.learning_spring.product.domain.model.Product;
import com.neversion.learning_spring.product.domain.port.out.ProductRepositoryPort;

@ExtendWith(MockitoExtension.class)
class CreateProductServiceTest {

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @InjectMocks
    private CreateProductService createProductService;

    @Test
    void shouldCreateProduct() {
        // ARRANGE
        Product product = new Product(1L, "Laptop", 1000.0, true);
        Product savedProduct = new Product(1L, "Laptop", 1000.0, true);

        // ACT
        when(productRepositoryPort.save(product)).thenReturn(savedProduct);
        Product result = createProductService.createProduct(product);

        // ASSERT
        assertEquals("Laptop", result.name());

        verify(productRepositoryPort).save(product);
    }
}
