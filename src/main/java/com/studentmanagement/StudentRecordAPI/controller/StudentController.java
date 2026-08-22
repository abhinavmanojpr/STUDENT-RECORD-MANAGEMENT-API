package com.studentmanagement.StudentRecordAPI.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.StudentRecordAPI.dto.request.StudentRequest;
import com.studentmanagement.StudentRecordAPI.dto.response.StudentResponse;
import com.studentmanagement.StudentRecordAPI.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<StudentResponse> createStudent(
            @Valid @RequestBody StudentRequest request) {

        StudentResponse createdStudent =
                studentService.createStudent(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    // PAGINATION
    @GetMapping("/page")
    public ResponseEntity<Page<StudentResponse>> getStudents(
            Pageable pageable) {

        Page<StudentResponse> students =
                studentService.getStudents(pageable);

        return ResponseEntity.ok(students);
    }

    // SEARCH
    @GetMapping("/search")
    public ResponseEntity<Page<StudentResponse>> searchStudents(
            @RequestParam String name,
            Pageable pageable) {

        Page<StudentResponse> students =
                studentService.searchStudents(name, pageable);

        return ResponseEntity.ok(students);
    }

    // FILTER
    @GetMapping("/filter")
    public ResponseEntity<?> filterStudents(
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge) {

        List<StudentResponse> students =
                studentService.filterStudents(
                        age,
                        email,
                        minAge,
                        maxAge
                );

        if (students.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No students found");
        }

        return ResponseEntity.ok(students);
    }

    // GET BY ID
    @GetMapping("/get")
    public ResponseEntity<StudentResponse> getStudent(
            @RequestParam Long id) {

        StudentResponse student =
                studentService.getStudentById(id);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(student);
    }

    // GET ALL
    @GetMapping("/getall")
    public ResponseEntity<Page<StudentResponse>> getAllStudents(
            Pageable pageable) {

        Page<StudentResponse> students =
                studentService.getAllStudents(pageable);

        return ResponseEntity.ok(students);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequest request) {

        StudentResponse student =
                studentService.updateStudent(id, request);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(student);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.ok(
                "Student deleted successfully"
        );
    }
}