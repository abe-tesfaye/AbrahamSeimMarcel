
# Nothin' But Net - Basketball Apparel API

## Overview

**Nothin' But Net** is a backend API service for managing basketball apparel, specifically focused on the providers who manage the products and sales information. This project is built using **Java Spring Boot**, **JPA/Hibernate**, **MySQL**, and is tested via **Postman**.


## Project Description

The **Nothin' But Net** API provides CRUD functionalities for managing providers, products, and sales records in a basketball apparel business. This application focuses on building the backend service for interacting with provider, product, and sales data, preparing for integration with a future frontend component.

## Features

- CRUD operations for **Providers**, **Products**, and **Sales** records.
- **Relational Database** using MySQL with normalized tables for `Provider`, `Product`, and `Sales`.
- **Role-Based Functionality** where providers can manage their profile, products, and sales statistics.
- **Error Handling** and validation to ensure data integrity and smooth operation.


## Dependencies

This project requires the following dependencies:

- **Spring Boot** - Provides the main framework and RESTful API support.
- **Spring Data JPA** - For database interactions with MySQL using JPA/Hibernate.
- **MySQL Connector** - Connects the Spring Boot application with the MySQL database.
- **Postman** - Used for testing and interacting with API endpoints.

## API Endpoints

### Provider API

- **Create Provider**: `POST /api/providers`
  - Request Body: `{ "username": "provider123", "password": "securePass" }`
- **Get All Providers**: `GET /api/providers`
- **Update Provider**: `PUT /api/providers/{id}`
  - Example: `/api/providers/1`
  - Request Body: `{ "username": "updatedName", "password": "newPass" }`
- **Delete Provider**: `DELETE /api/providers/{id}`

### Product API

- **Create Product**: `POST /api/products`
  - Request Body: `{ "name": "Jordan 4", "provider_id": 1, "email": "contact@provider.com", "stock": 100 }`
- **Get All Products**: `GET /api/products`
- **Get Product by ID**: `GET /api/products/{id}`
- **Update Product**: `PUT /api/products/{id}`
  - Example: `/api/products/2`
  - Request Body: `{ "name": "Updated Product", "stock": 80 }`
- **Delete Product**: `DELETE /api/products/{id}`

### Sales API

- **Create Sales Record**: `POST /api/sales`
  - Request Body: `{ "product_id": 1, "date": "2024-10-29", "quantity": 5 }`
- **Get All Sales Records**: `GET /api/sales`
- **Get Sales by ID**: `GET /api/sales/{id}`
- **Update Sales Record**: `PUT /api/sales/{id}`
  - Example: `/api/sales/3`
  - Request Body: `{ "product_id": 1, "quantity": 10, "date": "2024-11-01" }`
- **Delete Sales Record**: `DELETE /api/sales/{id}`

## Setup and Installation

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd <repository-folder>
   ```

2. **Configure the MySQL Database**
   - Ensure MySQL is running on your system.
   - Create a new database for this project in MySQL.
   - Update `src/main/resources/application.properties` with your database credentials.
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3307/nothingbutnet
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     spring.jpa.hibernate.ddl-auto=update
     ```

3. **Run the Application**
   - The application should start on `http://localhost:8080`.

4. **Testing the API with Postman**
   - Use the provided endpoints to interact with the API.
   - Example:
     - `POST /api/providers` to create a new provider.
     - `GET /api/products` to retrieve all products.

## Testing

To test the API, follow these steps:

1. **Create Providers**: Add a few providers using `POST /api/providers`.
2. **Add Products**: Use the provider IDs to add products in `POST /api/products`.
3. **Create Sales Records**: For each product, create a sales record using `POST /api/sales`.
4. **Update/Delete**: Use the PUT and DELETE endpoints to verify data manipulation.
5. **Check Relationships**: Ensure foreign key relationships are handled correctly, such as deleting a provider affecting products or sales data.

