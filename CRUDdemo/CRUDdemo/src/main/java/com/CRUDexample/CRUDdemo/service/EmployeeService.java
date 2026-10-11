package com.CRUDexample.CRUDdemo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRUDexample.CRUDdemo.entity.Employee;
import com.CRUDexample.CRUDdemo.repository.EmployeeRepository;

@Service
public class EmployeeService {

    // @Autowired
    private final EmployeeRepository repository;

    private EmployeeService(EmployeeRepository repository){
        this.repository = repository;
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Employee addEmployee(Employee employee) {
        return repository.save(employee);
    }

    public List<Employee> addallEmployee(List<Employee> employees) {
        return repository.saveAll(employees);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Employee existing = repository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(employee.getName());
            existing.setSalary(employee.getSalary());
            return repository.save(existing);
        }
        employee.setId(id);
        return repository.save(employee);
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}

