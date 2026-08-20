package com.studentmanagement.StudentRecordAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentmanagement.StudentRecordAPI.entity.Course;

public interface CourseRepository extends JpaRepository <Course,Long> {

    
} 