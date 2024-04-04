package com.anil.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FormController {
    @RequestMapping("/showForm")
    public String showForm(){
        return "form";
    }
    @RequestMapping("/processForm")
    public String processForm(){
        return "p-form";
    }
}
