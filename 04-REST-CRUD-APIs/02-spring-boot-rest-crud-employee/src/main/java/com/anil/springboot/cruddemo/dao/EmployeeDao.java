package com.anil.springboot.cruddemo.dao;

import com.anil.springboot.cruddemo.entity.Employee;
import java.util.List;


public interface EmployeeDao {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee theEmployee);
    void deleteById(int id);
}
