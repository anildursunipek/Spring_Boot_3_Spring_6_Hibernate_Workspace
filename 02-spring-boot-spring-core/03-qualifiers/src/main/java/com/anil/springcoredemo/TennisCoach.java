package com.anil.springcoredemo;

import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 30 minutes";
    }
}
