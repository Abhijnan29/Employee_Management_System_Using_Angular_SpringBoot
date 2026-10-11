package com.CRUDexample.CRUDdemo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.CRUDexample.CRUDdemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
