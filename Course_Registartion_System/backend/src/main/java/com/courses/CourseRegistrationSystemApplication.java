package com.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseRegistrationSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourseRegistrationSystemApplication.class, args);
        System.out.println("✅ Course Registration System Started Successfully!");
    }
}