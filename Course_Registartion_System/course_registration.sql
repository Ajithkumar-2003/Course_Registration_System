CREATE DATABASE course_db;
USE course_db;

CREATE TABLE courses (
    course_id VARCHAR(10) PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    trainer VARCHAR(100) NOT NULL,
    duration_in_weeks INT NOT NULL
);

select * from courses;

DELETE FROM courses;


CREATE TABLE enrolled_students (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email_id VARCHAR(150) NOT NULL,
    course_name VARCHAR(100) NOT NULL
);

select * from enrolled_students;

ALTER TABLE enrolled_students AUTO_INCREMENT = 1;

DELETE FROM enrolled_students;
