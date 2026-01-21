package com.neversion.learning_spring.product.integration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.neversion.learning_spring.product.infrastructure.adapters.out.persistence.JpaProductRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest // Carga todo el contexto de la aplicación Spring Boot
@AutoConfigureMockMvc // Configura un MockMvc para simular peticiones HTTP
@ActiveProfiles("dev") // Usa el perfil de prueba (configuración específica para tests)
class ProductIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JpaProductRepository productRepository;

    // public ProductIntegrationTest(MockMvc mockMvc, ProductRepository
    // productRepository) {
    // this.mockMvc = mockMvc;
    // this.productRepository = productRepository;
    // }

    @Test
    void shouldCreateAndFindProduct() throws Exception {
        productRepository.deleteAll(); // Limpiamos la base de datos antes de cada test

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Camera\", \"price\": 278.99}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Camera")));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Camera")));
    }
}
