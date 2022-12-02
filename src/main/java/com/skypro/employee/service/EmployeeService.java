package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (!StringUtils.isAlpha(employeeRequest.getFirstName()) ||
                !StringUtils.isAlpha(employeeRequest.getLastName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Employee employee = new Employee(
                StringUtils.capitalize(employeeRequest.getFirstName()),
                StringUtils.capitalize(employeeRequest.getLastName()),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());
        this.employees.put(employee.getId(), employee);
        return employee;
    }

    public int getSalarySum() {
        return employees.values().stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public Employee getEmployeeSalaryMin() {
        return employees.values().stream()
                .min(Comparator.comparing(Employee::getSalary))
                .get();
    }

    public Employee getEmployeeSalaryMax() {
        return employees.values().stream()
                .max(Comparator.comparing(Employee::getSalary))
                .get();
    }

    public List<Employee> getEmployeeHighSalary() {
        double averageSalary = employees.values().stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .getAsDouble();
        return employees.values().stream()
                .filter(e -> e.getSalary() > averageSalary)
                .collect(Collectors.toList());

    }
}
