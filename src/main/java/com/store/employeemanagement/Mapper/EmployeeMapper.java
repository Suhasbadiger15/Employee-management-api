package com.store.employeemanagement.Mapper;

import com.store.employeemanagement.dto.EmployeeRequest;
import com.store.employeemanagement.dto.EmployeeResponse;
import com.store.employeemanagement.model.Employee;

public class EmployeeMapper {

    public static Employee toEmployee(EmployeeRequest request) {

        Employee employee = new Employee();

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setSalary(request.getSalary());
        employee.setDepartment(request.getDepartment());

        return employee;
    }

    public static EmployeeResponse toResponse(Employee employee) {

        EmployeeResponse response = new EmployeeResponse();

        response.setId(employee.getId());
        response.setName(employee.getName());
        response.setEmail(employee.getEmail());
        response.setSalary(employee.getSalary());
        response.setDepartment(employee.getDepartment());

        return response;
    }

}