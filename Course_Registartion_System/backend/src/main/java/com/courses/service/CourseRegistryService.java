package com.courses.service;

import com.courses.model.CourseRegistry;
import com.courses.repository.CourseRegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseRegistryService {

    @Autowired
    private CourseRegistryRepository registryRepository;

    public List<CourseRegistry> getAllEnrollments() {
        return registryRepository.findAll();
    }

    public CourseRegistry registerStudent(String name, String emailId, String courseName) {
        // Validate input
        if (name == null || name.trim().isEmpty()) {
            throw new RuntimeException("Student name is required");
        }
        if (emailId == null || emailId.trim().isEmpty()) {
            throw new RuntimeException("Email ID is required");
        }
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new RuntimeException("Course name is required");
        }
        
        // Check if student is already registered for this course
        if (registryRepository.existsByEmailIdAndCourseName(emailId.trim(), courseName.trim())) {
            throw new RuntimeException("Student with email '" + emailId + "' is already registered for course '" + courseName + "'");
        }
        
        CourseRegistry registration = new CourseRegistry(
            name.trim(), 
            emailId.trim(), 
            courseName.trim()
        );
        
        return registryRepository.save(registration);
    }

    public void deleteEnrollment(Long id) {
        if (!registryRepository.existsById(id)) {
            throw new RuntimeException("Enrollment with ID " + id + " not found");
        }
        registryRepository.deleteById(id);
    }
}