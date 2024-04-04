package com.anil.springboot.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {
    @RequestMapping("/showForm")
    public String showForm(){
        return "form";
    }
/*    @RequestMapping("/processForm")
    public String processForm(HttpServletRequest request, Model model){
        String name = request.getParameter("studentName");
        name = name.toUpperCase();
        String message = "Hi! " + name;

        model.addAttribute("name", name);
        model.addAttribute("message", message);
        return "p-form";
    }*/
    @RequestMapping("/processForm")
    public String processForm(@RequestParam("studentName") String name, Model model){
        name = name.toUpperCase();
        String message = "Hi! " + name;

        model.addAttribute("name", name);
        model.addAttribute("message", message);
        return "p-form";
    }
}
