package com.neversion.learning_spring.product.infrastructure.adapters.in.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.neversion.learning_spring.exceptions.ResourceNotFoundException;
import com.neversion.learning_spring.product.domain.model.Product;
import com.neversion.learning_spring.product.application.port.in.*;
import com.neversion.learning_spring.product.infrastructure.adapters.in.rest.dto.*;
import com.neversion.learning_spring.product.infrastructure.adapters.in.rest.mapper.ProductMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final ProductMapper productMapper;

    public ProductController(CreateProductUseCase createProductUseCase, UpdateProductUseCase updateProductUseCase,
            DeleteProductUseCase deleteProductUseCase, GetProductUseCase getProductUseCase,
            ProductMapper productMapper) {
        this.createProductUseCase = createProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.getProductUseCase = getProductUseCase;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        Product newProduct = productMapper.toProduct(productRequest);
        Product savedProduct = createProductUseCase.createProduct(newProduct);
        ProductResponse productResponse = productMapper.toProductResponse(savedProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    @GetMapping
    public List<ProductResponse> getProducts() {
        List<Product> products = getProductUseCase.getAllProducts();
        return productMapper.toProductResponseList(products);
    }

    @Operation(summary = "Obtener un producto por ID", description = "Devuelve los detalles de un producto específico si existe y está activo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado con éxito"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado o inactivo")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {

        Product product = getProductUseCase.getProduct(id);
        ProductResponse productResponse = productMapper.toProductResponse(product);
        return ResponseEntity.ok(productResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id,
            @Valid @RequestBody ProductRequest productRequest) {
        Product product = updateProductUseCase.updateProduct(id);
        // .orElseThrow(() -> new ResourceNotFoundException("Product not found with id:
        // " + id));

        productMapper.updateProductFromRequest(product, productRequest);
        Product updatedProduct = updateProductUseCase.updateProduct(id);
        ProductResponse productResponse = productMapper.toProductResponse(updatedProduct);
        return ResponseEntity.ok(productResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        // Product product = deleteProductUseCase.deleteProduct(id);
        deleteProductUseCase.deleteProduct(id);
        // .orElseThrow(() -> new ResourceNotFoundException("Product not found with id:
        // " + id));
        // deleteProductUseCase.deleteProduct(product);
        return ResponseEntity.noContent().build();
    }
}
