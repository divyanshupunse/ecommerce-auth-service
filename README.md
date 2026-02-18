# E-Commerce Auth Service

## Overview
`ecommerce-auth-service` is the **authentication microservice** for the E-Commerce Microservices Application.  
It handles **user registration, login, and JWT token generation** for secure access to other microservices through the API Gateway.

---

## Features

- **User Signup** (CUSTOMER / ADMIN)
- **Login with JWT authentication**
- **Role-based access control**
- **Secure password storage** using Spring Security

---

## Architecture

- Built with **Spring Boot** and **Spring Security**
- Uses **Spring Data JPA** for database interactions
- **JWT** used for authentication
- Connects with **MySQL database** `user_db`



