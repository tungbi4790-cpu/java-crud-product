package com.example.product;

import com.example.product.dto.ProductRequest;
import com.example.product.dto.ProductResponse;
import com.example.product.exception.ProductNotFoundException;
import com.example.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void shouldCreateProduct() {
        ProductRequest request = new ProductRequest();
        request.setName("Test Product");
        request.setDescription("A test product");
        request.setPrice(new BigDecimal("29.99"));
        request.setQuantity(100);

        ProductResponse response = productService.createProduct(request);

        assertNotNull(response.getId());
        assertEquals("Test Product", response.getName());
        assertEquals(new BigDecimal("29.99"), response.getPrice());
    }

    @Test
    void shouldGetProductById() {
        ProductRequest request = new ProductRequest();
        request.setName("Findable Product");
        request.setDescription("Can be found");
        request.setPrice(new BigDecimal("15.00"));
        request.setQuantity(50);

        ProductResponse created = productService.createProduct(request);
        ProductResponse found = productService.getProductById(created.getId());

        assertEquals(created.getId(), found.getId());
        assertEquals("Findable Product", found.getName());
    }

    @Test
    void shouldThrowWhenProductNotFound() {
        assertThrows(ProductNotFoundException.class, () -> {
            productService.getProductById(99999L);
        });
    }

    @Test
    void shouldUpdateProduct() {
        ProductRequest request = new ProductRequest();
        request.setName("Original Name");
        request.setDescription("Original");
        request.setPrice(new BigDecimal("10.00"));
        request.setQuantity(5);

        ProductResponse created = productService.createProduct(request);

        request.setName("Updated Name");
        request.setPrice(new BigDecimal("20.00"));

        ProductResponse updated = productService.updateProduct(created.getId(), request);
        assertEquals("Updated Name", updated.getName());
        assertEquals(new BigDecimal("20.00"), updated.getPrice());
    }

    @Test
    void shouldDeleteProduct() {
        ProductRequest request = new ProductRequest();
        request.setName("To Delete");
        request.setDescription("Will be removed");
        request.setPrice(new BigDecimal("5.00"));
        request.setQuantity(1);

        ProductResponse created = productService.createProduct(request);
        productService.deleteProduct(created.getId());

        assertThrows(ProductNotFoundException.class, () -> {
            productService.getProductById(created.getId());
        });
    }

    // TODO: Add test for search functionality
    // TODO: Add test for pagination when implemented
    // TODO: Add test for duplicate product name validation
    // TODO: Add test for bulk import
    // TODO: Add integration tests for REST endpoints using MockMvc
}
