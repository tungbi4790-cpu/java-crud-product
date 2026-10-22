package com.example.product.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("Product not found with id: " + id);
    }

    // TODO: Add constructor that accepts 'sku' for lookup by SKU
    // TODO: Add constructor that accepts search criteria for better error messages
}
