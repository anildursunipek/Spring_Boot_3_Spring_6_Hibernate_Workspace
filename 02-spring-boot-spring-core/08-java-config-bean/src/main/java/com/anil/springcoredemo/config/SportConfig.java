package com.anil.springcoredemo.config;

import com.anil.springcoredemo.Coach;
import com.anil.springcoredemo.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean("aquatic") // Custom bean id
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
