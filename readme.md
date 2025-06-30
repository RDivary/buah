# 🍎 Buah CRUD App - Spring Boot + Thymeleaf + PostgreSQL

This project is a simple CRUD application with authentication and role-based access using Spring Boot, Thymeleaf, and PostgreSQL.

## 🚀 Features
- Register with role selection (`ADMIN` or `USER`)
- Login and logout
- `ADMIN` users can add, edit, and delete buah
- `USER` users can only view the buah list
- Frontend rendered using Thymeleaf templates
- Backend powered by Spring Boot and Spring Data JPA
- Data persisted in PostgreSQL

---

## 🧠 Design Flow

1. **Authentication**: Register and login handled via `AuthController`, storing user sessions
2. **Authorization**: Role saved in session, used to restrict UI access
3. **Separation of Concerns**:
    - `AuthController` → login, register, logout
    - `BuahController` → list, create, edit, delete buah
4. **Templates**:
    - `/` loads `index.html` showing all buahs
    - Conditional rendering via Thymeleaf (`th:if`) based on role
5. **Form actions**:
    - POST + hidden `_method` used to simulate `PATCH` and `DELETE`

---

## 🛠 How to Run

### 1. Clone the Repository
```bash
https://github.com/RDivary/buah.git
cd buah
```

### 2. Configure `application.properties`
Set your PostgreSQL connection:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.thymeleaf.cache=false
```

### 3. Run the Application
Using Maven Wrapper:
```bash
./mvnw spring-boot:run
```

Or run from JAR:
```bash
java -jar target/buah.jar
```

---

## 🌐 URL Endpoints

| Feature         | URL                      |
|-----------------|--------------------------|
| Home (redirect) | `http://localhost:8080/` |
| Login Page      | `/auth/login`            |
| Registration    | `/auth/register`         |
| Buah List       | `/`                      |
| Create Form     | `/buah/create`           |
| Edit Form       | `/buah/update/{id}`      |

---

## 🧪 Default Roles
When registering, users can choose their role. UI logic (buttons/forms) adapts based on whether the user is an `ADMIN` or `USER`.

---

## 📦 Dependencies
- Spring Boot Starter Web
- Spring Boot Starter Thymeleaf
- Spring Boot Starter Data JPA
- PostgreSQL Driver
- Lombok

---

Let us know if you’d like to expand this project further with REST APIs, JWT authentication, or a JavaScript-based frontend!
