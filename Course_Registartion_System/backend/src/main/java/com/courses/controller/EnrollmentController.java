package com.courses.controller;

import com.courses.model.CourseRegistry;
import com.courses.service.CourseRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "*")
public class EnrollmentController {

    @Autowired
    private CourseRegistryService registryService;

    @GetMapping("/enrolled")
    public ResponseEntity<List<CourseRegistry>> getEnrolledStudents() {
        try {
            List<CourseRegistry> enrollments = registryService.getAllEnrollments();
            return new ResponseEntity<>(enrollments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(
            @RequestParam String name,
            @RequestParam String emailId,
            @RequestParam String courseName) {
        
        try {
            CourseRegistry registration = registryService.registerStudent(name, emailId, courseName);
            return new ResponseEntity<>(registration, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while registering student", 
                                      HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/enrolled/{id}")
    public ResponseEntity<?> deleteEnrollment(@PathVariable Long id) {
        try {
            registryService.deleteEnrollment(id);
            return new ResponseEntity<>("Enrollment deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while deleting enrollment", 
                                      HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}