package com.store.employeemanagement.repository;

import com.store.employeemanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByDepartment(String department);

    List<Employee> findBySalaryGreaterThan(double salary);

    List<Employee> findByDepartmentAndSalaryGreaterThan(
            String department,
            double salary
    );

    List<Employee> findByNameContaining(String name);

    List<Employee> findByNameStartingWith(String name);

    List<Employee> findByNameEndingWith(String name);

}
