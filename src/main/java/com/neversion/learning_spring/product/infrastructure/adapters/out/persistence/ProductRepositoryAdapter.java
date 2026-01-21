package com.neversion.learning_spring.product.infrastructure.adapters.out.persistence;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.neversion.learning_spring.product.domain.model.Product;
import com.neversion.learning_spring.product.domain.port.out.ProductRepositoryPort;

@Repository
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final JpaProductRepository jpaProductRepository;
    private final ProductPersistenceMapper productMapper;

    public ProductRepositoryAdapter(JpaProductRepository jpaProductRepository, ProductPersistenceMapper productMapper) {
        this.jpaProductRepository = jpaProductRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = productMapper.toEntity(product);
        ProductEntity savedProduct = jpaProductRepository.save(productEntity);
        return productMapper.toDomain(savedProduct);
    }

    @Override
    public List<Product> findAll() {
        return jpaProductRepository.findAll().stream()
                .map(productMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaProductRepository.findById(id)
                .map(productMapper::toDomain);
    }

    @Override
    public Product update(Long id) {
        ProductEntity productEntity = jpaProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        jpaProductRepository.save(productEntity);
        return productMapper.toDomain(productEntity);
    }

    @Override
    public void delete(Long id) {
        jpaProductRepository.deleteById(id);
    }

}
