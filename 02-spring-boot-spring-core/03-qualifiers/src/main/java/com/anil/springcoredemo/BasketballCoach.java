package com.anil.springcoredemo;

import org.springframework.stereotype.Component;

@Component
public class BasketballCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 45 minutes";
    }
}
