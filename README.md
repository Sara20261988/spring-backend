# Personal Finance Management Backend

A RESTful backend application for managing personal finances, built with Spring Boot and PostgreSQL.

## Technologies

- Java 21
- Spring Boot 4.1.0
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Postman

## Architecture

The application follows a layered architecture:

Controller → Service → Repository → PostgreSQL

### Controller
Handles HTTP requests and responses.

### Service
Contains application and business logic.

### Repository
Uses Spring Data JPA to communicate with the database.

### DTOs
Control the data accepted by and returned from the REST API.

## Features

- Account management
- User management
- Transaction management
- Category management
- Budget management
- Financial summaries
- Category spending summaries
- Budget versus actual spending
- Request validation
- Global exception handling
- PostgreSQL persistence

## API Examples

### Accounts

```text
GET    /api/accounts
GET    /api/accounts/{id}
POST   /api/accounts
PUT    /api/accounts/{id}
DELETE /api/accounts/{id}