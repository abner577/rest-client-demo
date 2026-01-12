# Spring RestClient Demo

A simple Spring Boot application demonstrating how to make outbound HTTP requests using **Spring RestClient** (Spring Boot 3.2+).

This project calls a public external API (JSONPlaceholder) and exposes local REST endpoints that proxy those requests. The goal is to understand **where RestClient fits**, how outbound HTTP calls are structured in Spring MVC applications, and how HTTP Interfaces can reduce boilerplate.

---

## 🎯 Goals of This Project

- Learn how to make outbound HTTP calls in **Spring MVC** applications
- Understand RestClient as the modern replacement for `RestTemplate`
- Practice clean layering:
    - Thin controllers
    - Dedicated service/client layer for HTTP calls
- Learn how HTTP Interfaces work on top of RestClient


---

## 🛠 Tech Stack

- Java 17+
- 🌱 Spring Boot 3.2+
- 🌐 Spring Web (Spring MVC)
- RestClient
- JSONPlaceholder (external public API)

---

## 🌍 External API Used

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

## 📡 Available Endpoints

These endpoints proxy requests to JSONPlaceholder:

| Method | Endpoint              | Description               |
|------|-----------------------|---------------------------|
| GET  | `/api/posts`          | Get all posts             |
| GET  | `/api/posts/{id}`     | Get post by ID            |
| POST | `/api/posts`          | Create a post             |
| PUT  | `/api/posts/{id}`     | Update a post             |
| DELETE | `/api/posts/{id}`   | Delete a post             |
