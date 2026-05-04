#  FraudShield Backend

FraudShield is a Fake Review Detection System built using Spring Boot that detects suspicious reviews and provides admin moderation features.

---

##  Features

* User Registration & Login (JWT Authentication)
* Role-Based Authorization (USER / ADMIN)
* Submit & Track Reviews
* Fraud Detection using Scoring Mechanism
* Admin Review Moderation (Approve / Reject)
* Suspicious User Detection

---

##  Fraud Detection Logic

Fraud score is calculated based on:

* Duplicate or similar review text
* Too many reviews in a short time
* Repeated reviews on the same product

If fraud score ≥ 60 → Fake Review
Else → Genuine Review

---

##  Tech Stack

* Java
* Spring Boot
* Spring Security
* JWT Authentication
* Spring Data JPA
* MySQL

---

##  Authentication Flow

1. User logs in
2. JWT token is generated
3. Token is sent to frontend
4. Each request includes token in header

Example:
Authorization: Bearer token

---

##  API Endpoints

### Auth

* POST /auth/login

### User

* POST /users/register

### Reviews

* POST /reviews
* GET /reviews/my-reviews
* GET /reviews/stats (ADMIN)
* PUT /reviews/{id}/approve (ADMIN)
* PUT /reviews/{id}/reject (ADMIN)

---

##  Setup Instructions

1. Clone the repo

2. Configure MySQL in application.properties

3. Add environment variables:

   * DB_URL
   * DB_USERNAME
   * DB_PASSWORD
   * JWT_SECRET

4. Run:
   mvn clean install
   mvn spring-boot:run

---

##  What I Learned

* Spring Boot REST APIs
* JWT Authentication
* Role-Based Authorization
* Entity Relationships
* Real-world Backend Design

---

##  Author

Sujay
