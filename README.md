# Employee Management REST API

A backend REST API built with Spring Boot for managing employee information. The project demonstrates how a typical layered Spring Boot application works with REST APIs, DTOs, validation, exception handling, Spring Data JPA, Hibernate, and MySQL.

## Features

- Create an employee
- Get all employees
- Get an employee by ID
- Update an employee
- Delete an employee
- Search employees by name
- Filter employees by department
- Filter employees by salary
- Combine multiple search conditions
- Sort employees by salary
- Paginate employee results
- Request validation
- Clean JSON error responses
- DTO-based request and response handling
- Mapper for Entity ↔ DTO conversion

## Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Jakarta Bean Validation
- Lombok

## Architecture

The application follows a layered architecture:

```text
Client / Postman
       |
       v
Controller
       |
       v
Service
       |
       v
Repository
       |
       v
Spring Data JPA
       |
       v
Hibernate
       |
       v
MySQL
```

### Controller

The Controller handles HTTP requests and sends HTTP responses.

### Service

The Service contains the application's business logic and acts as a bridge between the Controller and Repository.

### Repository

The Repository communicates with the database through Spring Data JPA.

### Entity

The `Employee` entity represents employee data stored in the MySQL database.

### DTO

Request and response DTOs are used to control what data enters and leaves the API instead of exposing the entity directly.

### Mapper

The `EmployeeMapper` converts between `Employee` entities and DTOs.

## CRUD APIs

### Create Employee

```http
POST /employees
```

Example request:

```json
{
  "name": "Suhas",
  "email": "suhas@example.com",
  "salary": 40000,
  "department": "IT"
}
```

### Get All Employees

```http
GET /employees
```

### Get Employee by ID

```http
GET /employees/{id}
```

Example:

```http
GET /employees/1
```

### Update Employee

```http
PUT /employees/{id}
```

### Delete Employee

```http
DELETE /employees/{id}
```

## Search and Filtering

### Search by Name

The application uses a Spring Data JPA derived query:

```java
findByNameContaining(String name);
```

Example:

```http
GET /employees/search/name/su
```

`Containing` performs a partial match.

Conceptually, this is similar to:

```sql
WHERE name LIKE '%su%'
```

### Filter by Department

```java
findByDepartment(String department);
```

Example:

```http
GET /employees/department/IT
```

### Filter by Salary

```java
findBySalaryGreaterThan(double salary);
```

Example:

```http
GET /employees/salary/greater/40000
```

### Combined Conditions

```java
findByDepartmentAndSalaryGreaterThan(
    String department,
    double salary
);
```

This allows conditions such as:

```text
department = IT
AND
salary > 40000
```

## Sorting

Spring Data JPA's `Sort` is used for sorting employee records.

Example:

```http
GET /employees/sort/salary
```

For descending salary:

```http
GET /employees/sort/salary/desc
```

Conceptually:

```sql
ORDER BY salary ASC
```

or:

```sql
ORDER BY salary DESC
```

## Pagination

The API supports pagination using Spring Data JPA's `Pageable`.

Example:

```http
GET /employees/page?page=0&size=5
```

Here:

- `page=0` means the first page
- `size=5` means five employees per page

Pagination avoids loading a very large number of records into a single response.

## Validation

Employee request data is validated using Jakarta Bean Validation.

For example, invalid input such as:

```json
{
  "name": "",
  "email": "wrong",
  "salary": -1000,
  "department": ""
}
```

is rejected with validation errors instead of being accepted as a valid employee.

## Exception Handling

The application provides clean JSON error responses for situations such as:

- Employee not found
- Invalid request data
- Invalid API requests
- Unknown endpoints

This makes API errors easier for clients to understand.

## Database

The application uses MySQL with Spring Data JPA and Hibernate.

Example database configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

Do not commit real database passwords or other credentials to GitHub.

## How to Run Locally

### 1. Clone the repository

```bash
git clone <your-github-repository-url>
```

### 2. Create the MySQL database

```sql
CREATE DATABASE employee_db;
```

### 3. Configure MySQL

Update `application.properties` with your local MySQL username and password.

### 4. Start the application

Using Maven:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The application runs on:

```text
http://localhost:8080
```

You can test the API using Postman.

## Example Request Flow

For:

```http
GET /employees/search/name/su
```

the request flows through the application as:

```text
Postman
   |
   v
EmployeeController
   |
   v
EmployeeService
   |
   v
EmployeeRepository
   |
   v
Spring Data JPA
   |
   v
Hibernate
   |
   v
MySQL
   |
   v
Employee data
   |
   v
EmployeeMapper
   |
   v
EmployeeResponse JSON
```
