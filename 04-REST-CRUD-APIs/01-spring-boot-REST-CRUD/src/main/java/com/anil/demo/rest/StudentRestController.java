package com.anil.demo.rest;

import com.anil.demo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    @GetMapping("/students")
    public List<Student> getStudents(){
        List<Student> list = new ArrayList<Student>();
        list.add(new Student("Anil", "Ipek"));
        list.add(new Student("Gamze", "Ipek"));
        list.add(new Student("Hatice", "Ipek"));

        // Spring REST and Jackson will automatically convert POJOs to JSON
        return list;
    }
}
