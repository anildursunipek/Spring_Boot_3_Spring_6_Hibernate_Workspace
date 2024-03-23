package com.anil.springcoredemo;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach{

    public TennisCoach(){
        System.out.println("In Constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 30 minutes";
    }

    @PostConstruct
    public void doMyStartupStaff(){
        System.out.println("In doMyStartupStaff(): "+ getClass().getSimpleName());
    }

    @PreDestroy
    public void doMyCleanupStaff(){
        System.out.println("In doMyCleanupStaff(): "+ getClass().getSimpleName());
    }
}
