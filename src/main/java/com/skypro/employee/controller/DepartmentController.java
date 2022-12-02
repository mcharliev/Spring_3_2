package com.skypro.employee.controller;

import com.skypro.employee.model.Employee;
import com.skypro.employee.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
 class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/department/{id}/employees")
    public List<Employee> getDepartment(@PathVariable("id") int id) {
        return this.departmentService.getEmployeesFromDepartment(id);
    }

    @GetMapping("/department/{id}/salary/sum")
    public int getSalarySumOneDepartment(@PathVariable("id") int id) {
        return this.departmentService.getSalarySumOfDepartment(id);
    }

    @GetMapping("/department/{id}/salary/max")
    public int getMaxSalaryInDepartment(@PathVariable("id") int id) {
        return this.departmentService.getMaxSalaryOfDepartment(id);
    }

    @GetMapping("/department/{id}/salary/min")
    public int getMinSalaryInDepartment(@PathVariable("id") int id) {
        return this.departmentService.getMinSalaryOfDepartment(id);
    }

    @GetMapping("/department/employees")
    public Map<Integer, List<Employee>> getEmployeesSortedByDepartment() {
        return this.departmentService.getEmployeesByDepartment();
    }
}




