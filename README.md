# Employee Management REST API

A Spring Boot REST API for managing employee information.

## Technologies Used

- Java
- Spring Boot
- Spring Web
- Maven
- REST API
- In-memory storage

## Features

- Create an employee
- Get all employees
- Get employee by ID
- Find employees by name
- Update employee details
- Delete an employee
- Global exception handling
- DTOs for request and response
- Mapper for converting between entities and DTOs

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/employees` | Create employee |
| GET | `/employees` | Get all employees |
| GET | `/employees/{id}` | Get employee by ID |
| PUT | `/employees/{id}` | Update employee |
| DELETE | `/employees/{id}` | Delete employee |
| GET | `/employees/name/{name}` | Find employees by name |

## How to Run

1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Run `EmployeemanagementApplication.java`.
4. The application starts on the default Spring Boot port.

## Project Structure

```text
controller
service
repository
model
dto
exception
Mapper
