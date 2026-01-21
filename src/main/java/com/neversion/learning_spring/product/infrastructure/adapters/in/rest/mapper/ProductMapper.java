package com.neversion.learning_spring.product.infrastructure.adapters.in.rest.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.neversion.learning_spring.product.infrastructure.adapters.in.rest.dto.ProductRequest;
import com.neversion.learning_spring.product.infrastructure.adapters.in.rest.dto.ProductResponse;
import com.neversion.learning_spring.product.domain.model.Product;

@Component
public class ProductMapper {

    public Product toProduct(ProductRequest productRequest) {
        return new Product(
                null, // because is autoincremented
                productRequest.getName(),
                productRequest.getPrice(),
                true);
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.id(),
                product.name(),
                product.price());
    }

    public List<ProductResponse> toProductResponseList(List<Product> products) {
        // Convierte una lista de objetos Product en una lista de ProductResponse
        // mapeando cada elemento individualmente mediante el método toProductResponse.
        return products.stream()
                .map(this::toProductResponse)
                .collect(Collectors.toList());
    }

    // public void updateProductFromRequest(Product product, ProductRequest
    // productRequest) {
    // if (product == null || productRequest == null)
    // return;
    // product.setName(productRequest.getName());
    // product.setPrice(productRequest.getPrice());
    // }

}
