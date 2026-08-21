package com.studentmanagement.StudentRecordAPI.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.studentmanagement.StudentRecordAPI.dto.request.CourseRequest;
import com.studentmanagement.StudentRecordAPI.dto.response.CourseResponse;
import com.studentmanagement.StudentRecordAPI.service.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<CourseResponse> createCourse(
            @Valid @RequestBody CourseRequest request) {

        CourseResponse response =
                courseService.createCourse(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // GET ALL
    @GetMapping("/getall")
    public ResponseEntity<List<CourseResponse>> getAllCourses() {

        List<CourseResponse> courses =
                courseService.getAllCourses();

        return ResponseEntity.ok(courses);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(
            @PathVariable Long id) {

        CourseResponse course =
                courseService.getCourseById(id);

        return ResponseEntity.ok(course);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseRequest request) {

        CourseResponse response =
                courseService.updateCourse(id, request);

        return ResponseEntity.ok(response);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(
            @PathVariable Long id) {

        courseService.deleteCourse(id);

        return ResponseEntity.noContent().build();
    }
}