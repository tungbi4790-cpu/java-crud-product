package com.example.product.controller;

import com.example.product.dto.ProductRequest;
import com.example.product.dto.ProductResponse;
import com.example.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // TODO: Add Swagger/OpenAPI documentation with @Operation annotations
    // TODO: Add rate limiting to prevent API abuse
    // TODO: Add authentication and authorization (Spring Security)

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        // TODO: Add pagination parameters (@RequestParam page, size, sort)
        // TODO: Add filtering parameters (category, minPrice, maxPrice, status)
        List<ProductResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        // TODO: Add ETag support for HTTP caching
        ProductResponse product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request) {
        // TODO: Add location header pointing to the newly created resource
        ProductResponse product = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {
        // TODO: Support PATCH for partial updates (separate DTO with optional fields)
        ProductResponse product = productService.updateProduct(id, request);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        // TODO: Return 204 No Content and consider soft delete confirmation
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(
            @RequestParam String keyword) {
        // TODO: Add advanced search with multiple criteria (price range, category, etc.)
        // TODO: Return paginated results with total count
        List<ProductResponse> products = productService.searchProducts(keyword);
        return ResponseEntity.ok(products);
    }

    // TODO: Add POST /api/products/bulk endpoint for batch creation
    // TODO: Add GET /api/products/export endpoint for CSV/PDF export
    // TODO: Add GET /api/products/low-stock endpoint for inventory alerts
    // TODO: Add CORS configuration for frontend integration
}
