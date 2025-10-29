package com.courses.controller;

import com.courses.model.Course;
import com.courses.model.CourseRegistry;
import com.courses.repository.CourseRepository;
import com.courses.repository.CourseRegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiagnosticController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseRegistryRepository registryRepository;

    @GetMapping("/diagnostic")
    public String diagnostic() {
        List<Course> courses = courseRepository.findAll();
        List<CourseRegistry> enrollments = registryRepository.findAll();
        
        StringBuilder result = new StringBuilder();
        result.append("<h1>Course Registration System - Database Diagnostic</h1>");
        result.append("<h3 style='color: green;'>✅ Backend is running successfully!</h3>");
        
        result.append("<h2>Courses Table</h2>");
        result.append("<p>Total courses: ").append(courses.size()).append("</p>");
        result.append("<table border='1' style='border-collapse: collapse;'>");
        result.append("<tr style='background-color: #f2f2f2;'><th>ID</th><th>Name</th><th>Trainer</th><th>Duration</th></tr>");
        
        for (Course course : courses) {
            result.append("<tr>")
                  .append("<td>").append(course.getCourseId()).append("</td>")
                  .append("<td>").append(course.getCourseName()).append("</td>")
                  .append("<td>").append(course.getTrainer()).append("</td>")
                  .append("<td>").append(course.getDurationInWeeks()).append(" weeks</td>")
                  .append("</tr>");
        }
        result.append("</table>");
        
        result.append("<h2>Enrollments Table</h2>");
        result.append("<p>Total enrollments: ").append(enrollments.size()).append("</p>");
        result.append("<table border='1' style='border-collapse: collapse;'>");
        result.append("<tr style='background-color: #f2f2f2;'><th>ID</th><th>Name</th><th>Email</th><th>Course</th></tr>");
        
        for (CourseRegistry enrollment : enrollments) {
            result.append("<tr>")
                  .append("<td>").append(enrollment.getId()).append("</td>")
                  .append("<td>").append(enrollment.getName()).append("</td>")
                  .append("<td>").append(enrollment.getEmailId()).append("</td>")
                  .append("<td>").append(enrollment.getCourseName()).append("</td>")
                  .append("</tr>");
        }
        result.append("</table>");
        
        return result.toString();
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "✅ Course Registration System Backend is running!";
    }
}