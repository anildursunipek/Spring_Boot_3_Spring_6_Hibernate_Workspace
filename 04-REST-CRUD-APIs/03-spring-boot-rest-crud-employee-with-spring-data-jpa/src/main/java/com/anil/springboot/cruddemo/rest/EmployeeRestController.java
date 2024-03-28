package com.anil.springboot.cruddemo.rest;

import com.anil.springboot.cruddemo.entity.Employee;
import com.anil.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;

    // inject EmployeeDao ( Constructor Injection )
    @Autowired
    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    // expose "/employee" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Optional<Employee> getEmployee(@PathVariable int employeeId){
         Optional<Employee> employee = employeeService.findById(employeeId);

         if(employee == null)
             throw new RuntimeException("Employee id not found - " + employeeId);

         return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee employee = employeeService.save(theEmployee);
        return employee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployeeById(@PathVariable int employeeId){
        Optional<Employee> tempEmployee = employeeService.findById(employeeId);

        if(tempEmployee == null)
            throw  new RuntimeException("Employee id not found - " + employeeId);

        employeeService.deleteById(employeeId);
        return "Deleted employee id: " + employeeId;
    }
}
