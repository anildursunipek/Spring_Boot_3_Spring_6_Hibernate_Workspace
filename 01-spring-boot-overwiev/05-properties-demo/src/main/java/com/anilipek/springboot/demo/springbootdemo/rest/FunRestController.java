package com.anilipek.springboot.demo.springbootdemo.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    // Inject propertis
    @Value("${team.name}")
    private String teamName;

    @Value("${coach.name}")
    private String coachName;

    @GetMapping("teamInfo")
    public String getTeamInfo(){
        return "Coach Name: " + this.coachName + " Team Name: " + this.teamName;
    }

    @GetMapping("/")
    public String sayHello(){
        return "Hello World!!!";
    }

    // expose a new endpoint for "workout"
    @GetMapping("/workout")
    public String getDailyWorkout(){
        return "Running";
    }
}
