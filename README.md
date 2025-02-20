# Beesby API

Welcome to the Beesby API project! This repository contains the backend service built with Java and Spring Boot, designed to manage user data and interactions efficiently.

## Table of Contents

- [Starting the Application](#starting-the-application)
- [Environment Configuration](#environment-configuration)
- [Swagger UI Documentation](#swagger-ui-documentation)

## Starting the Application

To start the application, use Docker Compose to build and run the containers in detached mode. Run the following command in your terminal:

```bash
docker compose up --build -d
```

This command will:
- Build the application image based on the Dockerfile.
- Start the application in the background.

## Environment Configuration

Make sure to configure the environment variables in your `.env` file. Below is the required configuration:

```dotenv
# Environment setting
ENVIRONMENT=development

# PostgreSQL configuration
POSTGRESQL_URL=jdbc:postgresql://localhost:5432/api
POSTGRESQL_USERNAME=api
POSTGRESQL_PASSWORD=api

# JWT secret for authentication
JWT_SECRET=3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b
```

- **ENVIRONMENT**: Set this to your current environment (e.g., development, production).
- **POSTGRESQL_URL**: The JDBC URL for connecting to the PostgreSQL database.
- **POSTGRESQL_USERNAME**: The username for accessing the PostgreSQL database.
- **POSTGRESQL_PASSWORD**: The password for the PostgreSQL user.
- **JWT_SECRET**: A secret key used for signing JSON Web Tokens (JWT).

Make sure your PostgreSQL database is up and running before starting the application.

## Swagger UI Documentation

Once the application is running, you can access the Swagger UI for API documentation and testing:

[Swagger UI](http://localhost:8081/swagger-ui/index.html)

This interface allows you to explore the API endpoints and test them interactively.

---