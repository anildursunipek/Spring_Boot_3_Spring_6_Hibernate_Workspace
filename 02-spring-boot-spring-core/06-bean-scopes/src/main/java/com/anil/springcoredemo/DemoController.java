package com.anil.springcoredemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private Coach myCoach;
    private Coach anotherCoach;

    @Autowired
    public DemoController(
            @Qualifier("tennisCoach") Coach theCoach,
            @Qualifier("tennisCoach") Coach anotherCoach)
    {
        System.out.println("In Constructor: " + getClass().getSimpleName());
        this.myCoach = theCoach;
        this.anotherCoach = anotherCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return this.myCoach.getDailyWorkout();
    }

    @GetMapping("/check")
    public String check(){
        return "Comparing beans: myCoach == anotherCoach: " + (this.myCoach == this.anotherCoach);
    }
}
