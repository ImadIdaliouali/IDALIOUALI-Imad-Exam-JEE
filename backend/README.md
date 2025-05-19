# Credit Management System API

A robust Spring Boot-based backend system for managing credit applications and accounts with comprehensive security features and detailed API documentation.

## Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Authentication](#authentication)
- [Database](#database)
- [Project Structure](#project-structure)
- [Security](#security)
- [Default Users](#default-users)
- [Development](#development)
- [Contributing](#contributing)

## Features

- **User Authentication**: JWT-based stateless authentication with role-based access control
- **Role Management**: Support for multiple user roles (Admin, Employee, Client)
- **Credit Management**: Comprehensive APIs for credit application and management
- **Repayment Tracking**: APIs for tracking and managing loan repayments
- **Client Management**: APIs for managing client information
- **Interactive API Documentation**: Swagger UI for exploring and testing API endpoints
- **Data Validation**: Comprehensive input validation with meaningful error responses
- **H2 In-Memory Database**: Quick setup with pre-configured database
- **Exception Handling**: Global exception handling for consistent error responses

## Technology Stack

- **Java 17**: Latest LTS version of Java
- **Spring Boot 3.2.2**: Latest stable Spring Boot framework
- **Spring Security**: Authentication and authorization
- **Spring Data JPA**: Data access layer
- **H2 Database**: In-memory database for development
- **JWT (JSON Web Tokens)**: Stateless authentication
- **Swagger/OpenAPI 3**: API documentation
- **Lombok**: Reduce boilerplate code
- **Maven**: Dependency management and build tool
- **MapStruct**: Object mapping between DTOs and entities

## Getting Started

### Prerequisites

- JDK 17+
- Maven 3.6+

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/ImadIdaliouali/IDALIOUALI-Imad-Exam-JEE
   cd IDALIOUALI-Imad-Exam-JEE/backend
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The application will be accessible at `http://localhost:8080`.

## API Documentation

Once the application is running, you can access the interactive API documentation:

- **Swagger UI**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **OpenAPI Definition**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

The Swagger UI allows you to:
- Browse all available endpoints
- See request/response schemas
- Test API calls directly from the browser
- Authorize with JWT to test secured endpoints

## Authentication

The API uses JWT (JSON Web Token) for authentication:

1. **Register a new user**: POST `/api/auth/register`
2. **Login**: POST `/api/auth/login`
3. **Use the returned token**: Add the JWT token to the Authorization header for subsequent requests:
   ```
   Authorization: Bearer your_jwt_token
   ```

### Role-Based Access Control

The system supports multiple user roles:
- **ADMIN**: Full access to all endpoints
- **EMPLOYEE**: Access to client and credit management functions
- **CLIENT**: Limited access to their own credit information

Role-specific endpoints:
- Admin registration: `/api/auth/register/admin`
- Employee registration: `/api/auth/register/employee`
- Regular user registration: `/api/auth/register`

## Database

The application uses H2 in-memory database for development:

- **H2 Console**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- **JDBC URL**: `jdbc:h2:mem:creditsdb`
- **Username**: `sa`
- **Password**: `` (empty)

The database is automatically initialized with schema and sample data on startup.

## Project Structure
