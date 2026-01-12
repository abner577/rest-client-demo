# Spring Boot RestClient Demo

This project is a simple Spring Boot application that demonstrates how to use the **Spring RestClient** (introduced in Spring Framework 6 / Spring Boot 3.2) to make outbound HTTP calls to an external REST API.

The demo uses the public **JSONPlaceholder** API to perform basic CRUD-style operations and exposes its own REST endpoints that proxy those external calls.

---

## Purpose of This Project

The goal of this demo is to:

- Understand **where RestClient fits** in the Spring ecosystem
- Practice making **outbound HTTP calls** from a Spring MVC application
- Learn the **fluent RestClient API**
- See how **HTTP Interfaces** work with RestClient
- Keep controllers thin and delegate external calls to a service layer

This project is intentionally simple and focused on learning, not production hardening.

---

## Tech Stack

- Java 17+
- Spring Boot 3.2+
- Spring Web (Spring MVC)
- RestClient
- JSONPlaceholder (external public API)

---

## External API Used

This project calls the JSONPlaceholder API:
https://jsonplaceholder.typicode.com

Specifically, it uses the `/posts` resource to demonstrate:
- Fetching all posts
- Fetching a post by ID
- Creating a post
- Updating a post
- Deleting a post

No authentication is required.

---

## Key Concepts Demonstrated

### RestClient Basics
- Building a RestClient with a base URL
- Making blocking HTTP calls (`GET`, `POST`, `PUT`, `DELETE`)
- Setting headers and request bodies
- Retrieving and deserializing responses

---

## Available Endpoints

These endpoints proxy requests to JSONPlaceholder:

| Method | Endpoint              | Description               |
|------|-----------------------|---------------------------|
| GET  | `/api/posts`          | Get all posts             |
| GET  | `/api/posts/{id}`     | Get post by ID            |
| POST | `/api/posts`          | Create a post             |
| PUT  | `/api/posts/{id}`     | Update a post             |
| DELETE | `/api/posts/{id}`   | Delete a post             |
