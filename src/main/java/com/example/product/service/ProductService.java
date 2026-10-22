package com.example.product.service;

import com.example.product.dto.ProductRequest;
import com.example.product.dto.ProductResponse;
import com.example.product.exception.ProductNotFoundException;
import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // TODO: Add caching with @Cacheable for frequently accessed products
    // Consider using Redis or Caffeine as the cache provider

    public List<ProductResponse> getAllProducts() {
        // TODO: Implement pagination and sorting instead of returning all products
        // Use Pageable and return Page<ProductResponse> for large datasets
        return productRepository.findAll()
                .stream()
                .map(ProductResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return ProductResponse.fromEntity(product);
    }

    public ProductResponse createProduct(ProductRequest request) {
        // TODO: Check for duplicate product name before creating
        // TODO: Validate category exists if categoryId is provided
        // TODO: Generate SKU automatically if not provided

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        Product saved = productRepository.save(product);
        return ProductResponse.fromEntity(saved);
    }

    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        // TODO: Add optimistic locking with @Version to prevent concurrent update conflicts
        // TODO: Track field-level changes for audit logging

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        Product updated = productRepository.save(product);
        return ProductResponse.fromEntity(updated);
    }

    public void deleteProduct(Long id) {
        // TODO: Implement soft delete by changing status to DISCONTINUED
        // instead of permanently removing the record

        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }

    public List<ProductResponse> searchProducts(String keyword) {
        // TODO: Implement full-text search using Specification or Criteria API
        // TODO: Add search by price range, category, and status filters
        return productRepository.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(ProductResponse::fromEntity)
                .collect(Collectors.toList());
    }

    // TODO: Add bulk import method for CSV/Excel product data
    // TODO: Add export method to generate product reports
    // TODO: Add method to check and notify low-stock products
}
