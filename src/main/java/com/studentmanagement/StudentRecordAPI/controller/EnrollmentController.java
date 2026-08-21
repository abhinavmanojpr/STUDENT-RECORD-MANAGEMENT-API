package com.studentmanagement.StudentRecordAPI.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.studentmanagement.StudentRecordAPI.dto.request.EnrollmentRequest;
import com.studentmanagement.StudentRecordAPI.dto.response.EnrollmentResponse;
import com.studentmanagement.StudentRecordAPI.service.EnrollmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(
            EnrollmentService enrollmentService) {

        this.enrollmentService = enrollmentService;
    }

    
    @PostMapping("/create")
    public ResponseEntity<EnrollmentResponse> createEnrollment(
            @Valid @RequestBody EnrollmentRequest request) {

        EnrollmentResponse response =
                enrollmentService.createEnrollment(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    @GetMapping("/getall")
    public ResponseEntity<List<EnrollmentResponse>>
            getAllEnrollments() {

        return ResponseEntity.ok(
                enrollmentService.getAllEnrollments()
        );
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponse>
            getEnrollmentById(@PathVariable Long id) {

        return ResponseEntity.ok(
                enrollmentService.getEnrollmentById(id)
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentResponse>
            updateEnrollment(
                    @PathVariable Long id,
                    @Valid @RequestBody EnrollmentRequest request) {

        EnrollmentResponse response =
                enrollmentService.updateEnrollment(id, request);

        return ResponseEntity.ok(response);
    }

 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(
            @PathVariable Long id) {

        enrollmentService.deleteEnrollment(id);

        return ResponseEntity.noContent().build();
    }
}