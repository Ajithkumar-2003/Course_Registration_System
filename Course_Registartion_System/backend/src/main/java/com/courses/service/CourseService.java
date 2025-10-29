package com.courses.service;

import com.courses.model.Course;
import com.courses.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(String id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.orElse(null);
    }

    public Course createCourse(Course course) {
        // Check if course already exists
        if (courseRepository.existsByCourseId(course.getCourseId())) {
            throw new RuntimeException("Course with ID '" + course.getCourseId() + "' already exists");
        }
        
        // Validate required fields
        if (course.getCourseName() == null || course.getCourseName().trim().isEmpty()) {
            throw new RuntimeException("Course name is required");
        }
        if (course.getTrainer() == null || course.getTrainer().trim().isEmpty()) {
            throw new RuntimeException("Trainer name is required");
        }
        if (course.getDurationInWeeks() <= 0) {
            throw new RuntimeException("Duration must be greater than 0");
        }
        
        return courseRepository.save(course);
    }

    public Course updateCourse(String id, Course courseDetails) {
        // Check if course exists
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course with ID '" + id + "' not found");
        }
        
        // Validate required fields
        if (courseDetails.getCourseName() == null || courseDetails.getCourseName().trim().isEmpty()) {
            throw new RuntimeException("Course name is required");
        }
        if (courseDetails.getTrainer() == null || courseDetails.getTrainer().trim().isEmpty()) {
            throw new RuntimeException("Trainer name is required");
        }
        if (courseDetails.getDurationInWeeks() <= 0) {
            throw new RuntimeException("Duration must be greater than 0");
        }
        
        // Set the ID and save
        courseDetails.setCourseId(id);
        return courseRepository.save(courseDetails);
    }

    public void deleteCourse(String id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course with ID '" + id + "' not found");
        }
        courseRepository.deleteById(id);
    }
}