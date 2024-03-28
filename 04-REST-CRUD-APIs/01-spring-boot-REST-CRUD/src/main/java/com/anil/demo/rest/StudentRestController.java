package com.anil.demo.rest;

import com.anil.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> studentList;

    @PostConstruct
    public void loadData(){
        studentList = new ArrayList<Student>();
        studentList.add(new Student("Anil", "Ipek"));
        studentList.add(new Student("Gamze", "Ipek"));
        studentList.add(new Student("Hatice", "Ipek"));
    }
    @GetMapping("/students")
    public List<Student> getStudents(){
        // Spring REST and Jackson will automatically convert POJOs to JSON
        return studentList;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId){
        // By default, variables must match
        return studentList.get(studentId);
    }
}
