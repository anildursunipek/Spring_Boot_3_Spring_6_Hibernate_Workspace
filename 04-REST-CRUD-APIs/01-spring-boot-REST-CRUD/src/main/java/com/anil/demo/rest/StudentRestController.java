package com.anil.demo.rest;

import com.anil.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if( (studentId >= studentList.size()) || (studentId < 0)){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        // By default, variables must match
        return studentList.get(studentId);
    }
}
