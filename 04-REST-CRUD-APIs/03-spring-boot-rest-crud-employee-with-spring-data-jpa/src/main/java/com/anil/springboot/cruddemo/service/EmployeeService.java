package com.anil.springboot.cruddemo.service;

import com.anil.springboot.cruddemo.entity.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAll();
    Optional<Employee> findById(int id);
    Employee save(Employee theEmployee);
    void deleteById(int id);
}
