package com.anil.springboot.thymeleafdemo.controller;

import com.anil.springboot.thymeleafdemo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    // Injection
    @Value("${countries}")
    public List<String> countries;

    @Value("${languages}")
    public List<String> languages;

    @Value("${systems}")
    public List<String> systems;

    public StudentController(){}

    @GetMapping("/showStudentForm")
    public String showForm(Model theModel){
        Student theStudent = new Student();
        // theStudent.setFirstName("anil");
        //theStudent.setLastName("ipek");
        theModel.addAttribute("student", theStudent);
        theModel.addAttribute("countries", this.countries);
        theModel.addAttribute("languages", this.languages);
        theModel.addAttribute("systems", this.systems);
        return "student-registration-form";
    }

    @PostMapping("/processStudentForm")
    public String processStudentForm(@ModelAttribute("student") Student theStudent){
        System.out.println("theSudent: " + theStudent.getFirstName() + " " + theStudent.getLastName());
        System.out.println("Country: " + theStudent.getCountry());
        System.out.println("Language: " + theStudent.getFavoriteLanguage());
        System.out.println("Systems: " + theStudent.getFavoriteSystems());
        return "confirmation";
    }
}
