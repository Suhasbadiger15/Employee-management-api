# Employee Management REST API

A Spring Boot REST API for managing employee information using MySQL, Spring Data JPA, and Hibernate.

## Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- REST API
- Postman

## Project Overview

This project is an Employee Management REST API developed using Spring Boot.

The application provides RESTful APIs to perform CRUD operations on employee data and uses MySQL for persistent data storage.

Spring Data JPA is used for repository and database operations, while Hibernate is used as the JPA implementation for mapping Java objects to database tables.

The project also demonstrates Spring Data JPA derived query methods, searching, filtering, sorting, and pagination.

## Features

### CRUD Operations

- Create a new employee
- Get all employees
- Get employee by ID
- Update employee details
- Delete an employee

### Filtering

- Find employees by department
- Find employees whose salary is greater than a specified value
- Find employees by department and salary greater than a specified value

### Searching

The application supports searching employees by name using:

- Name starting with a specific value
- Name ending with a specific value
- Name containing a specific value

### Sorting

Employees can be sorted using:

- Ascending order
- Descending order

### Pagination

The application supports pagination using Spring Data JPA's `Pageable`.

This allows employee records to be retrieved page by page instead of retrieving all records at once.

### Exception Handling

- Custom `EmployeeNotFoundException`
- Global exception handling using `@ControllerAdvice`
- Structured error responses

### DTOs

The project uses separate DTOs for handling API requests and responses:

- `EmployeeRequest`
- `EmployeeResponse`

This keeps the API layer separate from the database entity.

### Entity Mapping

An `EmployeeMapper` is used to convert between:

- Employee entity
- EmployeeRequest DTO
- EmployeeResponse DTO

## Spring Data JPA Derived Queries

The project uses Spring Data JPA derived query methods to perform database operations without manually writing SQL queries for every operation.

## Project Structure

```text
src/main/java/com/store/employeemanagement/

├── controller
│   └── EmployeeController.java
│
├── service
│   └── EmployeeService.java
│
├── repository
│   └── EmployeeRepository.java
│
├── model
│   └── Employee.java
│
├── dto
│   ├── EmployeeRequest.java
│   └── EmployeeResponse.java
│
├── Mapper
│   └── EmployeeMapper.java
│
└── exception
    ├── EmployeeNotFoundException.java
    ├── ErrorResponse.java
    └── GlobalExceptionHandler.java
