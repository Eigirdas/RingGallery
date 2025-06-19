# 💍 Ring Gallery – Backend

This is the **backend** part of the Ring Gallery project. It is built using **Spring Boot** and provides secure REST APIs to support user registration, authentication, ring management, and image upload.

> 📦 The frontend can be found here: [Ring Gallery Frontend](https://github.com/Eigirdas/ring-gallery-front)

---

## 🚀 Features

- ✅ User registration and login with role-based authentication
- 🧑 Users can:
  - View all rings (even without logging in)
  - Create, update, and delete their own rings
  - Upload and update ring images
- 👑 Admin can:
  - View and manage all users and rings
  - Perform all user actions plus admin-specific tasks
- 🖼️ Image upload and download via multipart file support
- 🔐 Authentication and authorization via Spring Security

---

## ⚠️ Important Note

A **dummy admin user** is automatically created on first run to simplify testing:

- Username: `dummy`
- Password: _[set in code or config]_

**Please delete this user after the first startup for security reasons!**  
You’ll find this logic in `SecurityInitConfiguration.java`.

---

## 🧰 Tech Stack

- Java 17+
- Spring Boot (Web, Security, JPA, Validation)
- Hibernate (JPA implementation)
- Multipart file handling for images
- Swagger UI for API testing

---

## 🛠️ Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- A running SQL database (e.g., PostgreSQL, MySQL)

### Clone and Run

```bash
git clone https://github.com/Eigirdas/RingGallery.git
cd RingGallery
mvn clean install
mvn spring-boot:run
