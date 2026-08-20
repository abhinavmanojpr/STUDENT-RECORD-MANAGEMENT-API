package com.studentmanagement.StudentRecordAPI.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.studentmanagement.StudentRecordAPI.entity.Enrollment;
import com.studentmanagement.StudentRecordAPI.service.EnrollmentService;

@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/create")
    public Enrollment createEnrollment(
            @RequestParam Long studentId,
            @RequestParam Long courseId) {

        return enrollmentService.createEnrollment(studentId, courseId);
    }

    @GetMapping("/getall")
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("/{id}")
    public Enrollment getEnrollmentById(@PathVariable Long id) {
        return enrollmentService.getEnrollmentById(id);
    }

    @PutMapping("/{id}")
public Enrollment updateEnrollment(
        @PathVariable Long id,
        @RequestParam Long studentId,
        @RequestParam Long courseId,
        @RequestParam String status) {

        return enrollmentService.updateEnrollment(
            id,
            studentId,
            courseId,
            status
        );
    }

    @DeleteMapping("/{id}")
    public void deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
    }
}