package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

  private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    public List<Employee> getEmployeesFromDepartment(int department) {
        return employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public int getSalarySumOfDepartment(int department) {
        return getEmployeesFromDepartment(department).stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public Map<Integer, List<Employee>> getEmployeesByDepartment() {
        return employeeService.getAllEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public int getMinSalaryOfDepartment(int department) {
        return getEmployeesFromDepartment(department).stream()
                .mapToInt(Employee::getSalary)
                .min().orElseThrow();
    }


    public int getMaxSalaryOfDepartment(int department) {
        return getEmployeesFromDepartment(department).stream()
                .mapToInt(Employee::getSalary)
                .max().orElseThrow();
    }
}
