package com.skypro.employee;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class EmployeeServiceTest {
    EmployeeService employeeService;
    Employee firstEmployee;
    Employee secondEmployee;
    Employee thirdEmployee;


    @BeforeEach
    void setEmployeeService() {
        employeeService = new EmployeeService();
        firstEmployee = employeeService.addEmployee(new EmployeeRequest("Алексей",
                "Алексеев",
                1,
                1500));
        secondEmployee = employeeService.addEmployee(new EmployeeRequest("Александр",
                "Александров",
                1,
                3500));
        thirdEmployee = employeeService.addEmployee(new EmployeeRequest("Михаил",
                "Михайлов",
                1,
                2750));
    }

    @Test
    void shouldGetAllEmployees() {
        Collection<Employee> actual = employeeService.getAllEmployees();
        assertEquals(3, actual.size());
        Assertions.assertTrue(actual.containsAll(List.of(firstEmployee, secondEmployee, thirdEmployee)));

    }
    @Test
    void shouldAddEmployee() {
        EmployeeRequest request = new EmployeeRequest("Employee", "Employee", 1, 3500);
        Employee employee = employeeService.addEmployee(request);
        assertEquals(employee.getFirstName(), request.getFirstName());
        assertEquals(employee.getLastName(), request.getLastName());
        assertEquals(employee.getDepartment(), request.getDepartment());
        assertEquals(employee.getSalary(), request.getSalary());
    }

    @Test
    void shouldGetSumOfSalary(){
        assertEquals(7750,employeeService.getSalarySum());
    }
    @Test
    void shouldGetMinSalary(){
        assertEquals(1500,employeeService.getEmployeeSalaryMin().getSalary());
    }
    @Test
    void shouldGetMaxSalary(){
        assertEquals(3500,employeeService.getEmployeeSalaryMax().getSalary());
    }

    @Test
    void shouldGetHighSalary(){
        assertIterableEquals(List.of(secondEmployee,thirdEmployee), employeeService.getEmployeeHighSalary());
    }

}

