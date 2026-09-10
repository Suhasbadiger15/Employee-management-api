package com.store.employeemanagement.service;


    import com.store.employeemanagement.exception.EmployeeNotFoundException;
    import com.store.employeemanagement.model.Employee;
    import com.store.employeemanagement.repository.EmployeeRepository;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.PageRequest;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.domain.Sort;
    import org.springframework.stereotype.Service;

    import java.util.ArrayList;
    import java.util.List;

    @Service
    public class EmployeeService {

        private final EmployeeRepository repository;

        public EmployeeService(EmployeeRepository repository) {
            this.repository = repository;
        }

        public List<Employee> getAllEmployees() {
            return repository.findAll();
        }

        public Employee addEmployee(Employee employee) {
            return repository.save(employee);
        }

        public Employee getEmployeebyId(int id)
        {
            return repository.findById(id)
                    .orElseThrow(() ->
                            new EmployeeNotFoundException(
                                    "Employee with id " + id + " not found"
                            )
                    );
        }

        public Employee updateEmployee(int id,Employee updatedEmployee)
        {
            Employee employee = repository.findById(id)
                    .orElseThrow(() ->
                            new EmployeeNotFoundException(
                                    "Employee with id " + id + " not found"
                            )
                    );

            employee.setName(updatedEmployee.getName());
            employee.setEmail(updatedEmployee.getEmail());
            employee.setSalary(updatedEmployee.getSalary());
            employee.setDepartment(updatedEmployee.getDepartment());

            return repository.save(employee);
        }

        public void deleteEmployeebyId(int id)
        {
            if (!repository.existsById(id)) {
                throw new EmployeeNotFoundException(
                        "Employee with id " + id + " not found"
                );
            }

            repository.deleteById(id);
        }

        public List<Employee> getEmployeesByDepartment(String department) {
            return repository.findByDepartment(department);
        }

        public List<Employee> getEmployeesBySalaryGreaterThan(double salary) {
            return repository.findBySalaryGreaterThan(salary);
        }

        public List<Employee> findByNameContaining(String name) {
            return repository.findByNameContaining(name);
        }

        public List<Employee> findByNameStartingWith(String name) {
            return repository.findByNameStartingWith(name);
        }

        public List<Employee> findByNameEndingWith(String name) {
            return repository.findByNameEndingWith(name);
        }

        public List<Employee> getEmployeesByDepartmentAndSalary(
                String department,
                double salary) {

            return repository.findByDepartmentAndSalaryGreaterThan(
                    department,
                    salary
            );
        }

        public List<Employee> getEmployeesSortedBySalary() {

            return repository.findAll(
                    Sort.by("salary")
            );
        }

        public List<Employee> getEmployeesSortedBySalaryDescending() {

            return repository.findAll(
                    Sort.by("salary").descending()
            );
        }

        public Page<Employee> getEmployees(int page, int size) {

            Pageable pageable = PageRequest.of(page, size);

            return repository.findAll(pageable);
        }
    }
