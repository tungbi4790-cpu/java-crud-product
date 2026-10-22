package com.example.product.repository;

import com.example.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    // TODO: Add method findByCategoryId(Long categoryId) for category filtering
    // TODO: Add method findByStatus(ProductStatus status) for status filtering
    // TODO: Add custom @Query for full-text search across name and description
    // TODO: Add pagination support with Pageable parameter
    // TODO: Add method findByQuantityLessThan(int threshold) for low-stock alerts
}
