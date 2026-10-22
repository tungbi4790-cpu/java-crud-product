# Product CRUD API

A Spring Boot REST API for product management with CRUD operations.

## Tech Stack
- Java 17
- Spring Boot 3.2
- Spring Data JPA
- H2 Database (dev) 
- Bean Validation

## Quick Start

```bash
# Build
./mvnw clean package

# Run
./mvnw spring-boot:run
```

The API runs at `http://localhost:8080`

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/products` | List all products |
| GET | `/api/products/{id}` | Get product by ID |
| POST | `/api/products` | Create a product |
| PUT | `/api/products/{id}` | Update a product |
| DELETE | `/api/products/{id}` | Delete a product |
| GET | `/api/products/search?keyword=` | Search products |

## Example Request

```json
POST /api/products
{
  "name": "Wireless Mouse",
  "description": "Ergonomic wireless mouse with USB receiver",
  "price": 29.99,
  "quantity": 150
}
```

## H2 Console

Access at `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:productdb`
- Username: `sa`
- Password: _(empty)_

## TODOs

This project contains `// TODO` comments throughout the codebase for future improvements:

- **Model**: Category entity, product images, SKU, status enum
- **Repository**: Category filtering, pagination, low-stock queries
- **Service**: Caching, pagination, soft delete, audit logging, bulk import/export
- **Controller**: Swagger docs, authentication, PATCH support, rate limiting
- **Config**: Production database, logging, connection pooling
- **Tests**: Search tests, pagination tests, MockMvc integration tests
