package com.CRUDexample.CRUDdemo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.CRUDexample.CRUDdemo.entity.Employee;
import com.CRUDexample.CRUDdemo.service.EmployeeService;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // GET all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // GET employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);

        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(employee);
    }


    // POST - add new employee
    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    // POST - add multiple employees(Bulk)
    @PostMapping("/employees")
    public List<Employee> addallEmployees(@RequestBody List<Employee> employees) {
        return employeeService.addallEmployee(employees);
    }
    

    // PUT - update employee
    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    // DELETE - delete employee
    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "Employee with id " + id + " deleted.";
    }
}
