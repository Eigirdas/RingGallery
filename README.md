Ring Gallery - Backend
This is the backend service for the Ring Gallery application, built with Spring Boot. It provides REST APIs to manage users, rings, and ring images, along with authentication and role-based access control.

Features
User registration and login with authentication

Admin and User roles with different permissions

Users can create, edit, and delete their own rings and ring photos

Admin can manage all users, rings, and associated data

Ring browsing available to unauthenticated users

Image upload and retrieval support for ring photos

Important Notes
In SecurityInitConfiguration.java, there is a default admin user named Dummy created on first run for testing and administration.

This Dummy user should be deleted after the initial setup for security reasons.

Technologies
Java 17+

Spring Boot (Web, Security, Validation, JPA)

Hibernate / JPA for persistence

JWT or session-based authentication (whichever you use)

Multipart file upload for ring images

Getting Started
Prerequisites
Java 17+

Maven 3.8+

A configured database (e.g., PostgreSQL, MySQL) — configure application.properties accordingly

Running the Application
Clone this repository:

bash
Copy
Edit
git clone https://github.com/Eigirdas/RingGallery.git
cd RingGallery
Configure your database in src/main/resources/application.properties (or use environment variables).

Build and run the backend:

bash
Copy
Edit
mvn clean install
mvn spring-boot:run
The backend will be available at http://localhost:8080

Access API documentation via Swagger UI at:
http://localhost:8080/swagger-ui/index.html

Frontend
The frontend application is in a separate repository:
https://github.com/Eigirdas/ring-gallery-front

API Overview
/auth/** — user registration, login, logout

/rings/** — ring CRUD operations

/images/** — image upload and retrieval

Admin routes are prefixed with /admin/

Security
Admin and User roles enforced in backend

Default admin user Dummy created on startup; delete this user as soon as possible

Authentication required for ring creation and modification

Contribution
Feel free to fork, improve, and create pull requests!
