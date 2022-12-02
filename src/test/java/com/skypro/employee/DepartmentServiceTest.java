package com.skypro.employee;

import com.skypro.employee.model.Employee;
import com.skypro.employee.service.DepartmentService;
import com.skypro.employee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    Employee employee1;
    Employee employee2;
    Employee employee3;

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        employee1 = new Employee("Алексей",
                "Алексеев",
                1,
                2000);
        employee2 = new Employee("Александр",
                "Александров",
                1,
                3000);
        employee3 = new Employee("Михаил",
                "Михайлов",
                2,
                4000);

        when(employeeService.getAllEmployees()).thenReturn(Set.of(employee1, employee2, employee3));
    }

    @Test
    void shouldGetAllEmployeesFromDepartment() {
        Collection<Employee> actual = departmentService.getEmployeesFromDepartment(1);
        assertEquals(2, actual.size());
        assertTrue(actual.containsAll(List.of(employee1, employee2)));
    }

    @Test
    void shouldGetSalarySumOfDepartment() {
        assertEquals(departmentService.getSalarySumOfDepartment(1), 5000);
    }

    @Test
    void shouldGetMaxSalaryFromDepartment() {
        assertEquals(departmentService.getMaxSalaryOfDepartment(1), 3000);
    }

    @Test
    void shouldGetMinSalaryFromDepartment() {
        assertEquals(departmentService.getMinSalaryOfDepartment(1), 2000);
    }

    @Test
    void shouldGetEmployeesByDepartment() {
        assertTrue(departmentService.getEmployeesByDepartment().get(1)
                .containsAll(List.of(employee1,employee2)));

        assertTrue(departmentService.getEmployeesByDepartment().get(2)
                .containsAll(List.of(employee3)));

    }
}
