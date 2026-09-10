package com.store.employeemanagement.controller;

import com.store.employeemanagement.Mapper.EmployeeMapper;
import com.store.employeemanagement.dto.EmployeeRequest;
import com.store.employeemanagement.dto.EmployeeResponse;
import com.store.employeemanagement.model.Employee;
import com.store.employeemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = service.getAllEmployees();

        List<EmployeeResponse> responses = new ArrayList<>();

        for (Employee employee : employees) {
            responses.add(EmployeeMapper.toResponse(employee));
        }

        return responses;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> addEmployee(@Valid @RequestBody EmployeeRequest request) {
        Employee employee = EmployeeMapper.toEmployee(request);

        Employee savedEmployee = service.addEmployee(employee);

        EmployeeResponse response = EmployeeMapper.toResponse(savedEmployee);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeebyId(@PathVariable int id){
        Employee employee = service.getEmployeebyId(id);
        return ResponseEntity.ok(EmployeeMapper.toResponse(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable int id,
            @Valid @RequestBody EmployeeRequest request) {

        Employee updatedEmployee = EmployeeMapper.toEmployee(request);

        Employee employee = service.updateEmployee(id, updatedEmployee);

        EmployeeResponse response = EmployeeMapper.toResponse(employee);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteEmpoyeebyId(@PathVariable int id){
        service.deleteEmployeebyId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/department/{department}")
    public List<EmployeeResponse> getEmployeesByDepartment(
           @Valid @PathVariable String department) {

        List<Employee> employees =
                service.getEmployeesByDepartment(department);

        List<EmployeeResponse> responses = new ArrayList<>();

        for (Employee employee : employees) {
            responses.add(EmployeeMapper.toResponse(employee));
        }

        return responses;
    }

    @GetMapping("/search/name/{name}")
    public List<EmployeeResponse> searchByName(
            @Valid @PathVariable String name) {

        List<Employee> employees =
                service.findByNameContaining(name);

        List<EmployeeResponse> responses = new ArrayList<>();

        for (Employee employee : employees) {
            responses.add(EmployeeMapper.toResponse(employee));
        }

        return responses;
    }

    @GetMapping("/search/name/start/{name}")
    public List<EmployeeResponse> findByNamestartingWith(@Valid @PathVariable String name) {

        List<Employee> employees =
                service.findByNameStartingWith(name);

        List<EmployeeResponse> responses = new ArrayList<>();

        for (Employee employee : employees) {
            responses.add(EmployeeMapper.toResponse(employee));
        }

        return responses;
    }

    @GetMapping("/search/name/end/{name}")
    public List<EmployeeResponse> findByNameendingWith(@Valid @PathVariable String name) {

        List<Employee> employees =
                service.findByNameStartingWith(name);

        List<EmployeeResponse> responses = new ArrayList<>();

        for (Employee employee : employees) {
            responses.add(EmployeeMapper.toResponse(employee));
        }

        return responses;
    }

    @GetMapping("/salary/greater/{salary}")
    public List<EmployeeResponse> getBySalaryGreaterThan(
            @Valid @PathVariable double salary) {

        List<Employee> employees =
                service.getEmployeesBySalaryGreaterThan(salary);

        List<EmployeeResponse> responses = new ArrayList<>();

        for (Employee employee : employees) {
            responses.add(EmployeeMapper.toResponse(employee));
        }

        return responses;
    }

    @GetMapping("/department/{department}/salary/greater/{salary}")
    public List<EmployeeResponse> getByDepartmentAndSalary(
            @Valid @PathVariable String department,
            @Valid @PathVariable double salary) {

        List<Employee> employees =
                service.getEmployeesByDepartmentAndSalary(
                        department,
                        salary
                );

        List<EmployeeResponse> responses = new ArrayList<>();

        for (Employee employee : employees) {
            responses.add(EmployeeMapper.toResponse(employee));
        }

        return responses;
    }

    @GetMapping("/sort/salary")
    public List<EmployeeResponse> sortBySalary() {

        List<Employee> employees =
                service.getEmployeesSortedBySalary();

        List<EmployeeResponse> responses = new ArrayList<>();

        for (Employee employee : employees) {
            responses.add(EmployeeMapper.toResponse(employee));
        }

        return responses;
    }


    @GetMapping("/sort/salary/desc")
    public List<EmployeeResponse> sortBySalaryDescending() {

        List<Employee> employees =
                service.getEmployeesSortedBySalaryDescending();

        List<EmployeeResponse> responses = new ArrayList<>();

        for (Employee employee : employees) {
            responses.add(EmployeeMapper.toResponse(employee));
        }

        return responses;
    }

    @GetMapping("/page")
    public Page<EmployeeResponse> getEmployees(
            @RequestParam int page,
            @RequestParam int size) {

        Page<Employee> employees =
                service.getEmployees(page, size);

        return employees.map(EmployeeMapper::toResponse);
    }

}