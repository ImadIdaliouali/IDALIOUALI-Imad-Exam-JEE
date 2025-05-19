# IDALIOUALI-Imad-Exam-JEE

This project consists of a full-stack application with Spring Boot backend and Angular frontend.

## Table of Contents

- [Backend (Spring Boot)](#backend-spring-boot)
  - [Technologies](#backend-technologies)
  - [Setup and Installation](#backend-setup)
  - [Project Structure](#backend-structure)
  - [API Endpoints](#api-endpoints)
- [Frontend (Angular)](#frontend-angular)
  - [Technologies](#frontend-technologies)
  - [Setup and Installation](#frontend-setup)
  - [Project Structure](#frontend-structure)
  - [Features](#frontend-features)
- [Running the Application](#running-the-application)

## Backend (Spring Boot) <a name="backend-spring-boot"></a>

### Technologies <a name="backend-technologies"></a>

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Spring Web
- Spring Security (if applicable)
- MySQL / H2 Database
- Maven

### Setup and Installation <a name="backend-setup"></a>

1. Ensure you have Java 17 and Maven installed
2. Clone the repository
3. Navigate to the backend directory

```bash
cd backend
```

4. Install dependencies and build the project

```bash
mvn clean install
```

5. Run the application

```bash
mvn spring-boot:run
```

### Project Structure <a name="backend-structure"></a>

```
src/main/java/com/example/demo/
├── controllers/      # REST controllers
├── entities/         # JPA entities
├── repositories/     # Spring Data JPA repositories
├── services/         # Business logic services
├── dtos/             # Data Transfer Objects
├── exceptions/       # Custom exceptions
└── config/           # Configuration classes
```

### API Endpoints <a name="api-endpoints"></a>

- `GET /api/resource` - Get all resources
- `GET /api/resource/{id}` - Get resource by ID
- `POST /api/resource` - Create a new resource
- `PUT /api/resource/{id}` - Update a resource
- `DELETE /api/resource/{id}` - Delete a resource

## Frontend (Angular) <a name="frontend-angular"></a>

### Technologies <a name="frontend-technologies"></a>

- Angular 16+
- TypeScript
- Angular Material
- RxJS
- Angular Router
- HTTP Client

### Setup and Installation <a name="frontend-setup"></a>

1. Ensure you have Node.js (v14+) and npm installed
2. Navigate to the frontend directory

```bash
cd frontend
```

3. Install dependencies

```bash
npm install
```

4. Run the development server

```bash
ng serve
```

5. Open your browser at `http://localhost:4200`

### Project Structure <a name="frontend-structure"></a>

```
frontend/
├── src/
│   ├── app/
│   │   ├── components/    # UI components
│   │   ├── services/      # API services
│   │   ├── models/        # Type definitions
│   │   ├── guards/        # Route guards
│   │   └── pipes/         # Custom pipes
│   ├── assets/            # Static assets
│   └── environments/      # Environment configurations
└── angular.json          # Angular configuration
```

### Features <a name="frontend-features"></a>

- Responsive UI with Angular Material
- Form validation
- HTTP communication with backend
- Routing with authentication (if applicable)
- State management

## Running the Application <a name="running-the-application"></a>

### Backend

```bash
cd backend
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`.

### Frontend

```bash
cd frontend
ng serve
```

The frontend will be available at `http://localhost:4200`.
