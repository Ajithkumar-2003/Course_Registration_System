package com.courses.repository;

import com.courses.model.CourseRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRegistryRepository extends JpaRepository<CourseRegistry, Long> {
    boolean existsByEmailIdAndCourseName(String emailId, String courseName);
}