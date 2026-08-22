package com.studentmanagement.StudentRecordAPI.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.studentmanagement.StudentRecordAPI.dto.response.StudentResponse;
import com.studentmanagement.StudentRecordAPI.dto.request.StudentRequest;
import com.studentmanagement.StudentRecordAPI.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/v1/students")
public class StudentController{

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }

    @PostMapping("/create")
    public  ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentRequest s){
        StudentResponse createdStudent=studentService.createStudent(s);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);

    }

    @GetMapping("/page")
    public ResponseEntity<Page<StudentResponse>> getStudents(
        Pageable pageable) {

        Page<StudentResponse> students =
            studentService.getStudents(pageable);

        return ResponseEntity.ok(students);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<StudentResponse>> searchStudents(
        @RequestParam String name,
        Pageable pageable) {

        Page<StudentResponse> students =studentService.searchStudents(name, pageable);

        return ResponseEntity.ok(students);
    }

   @GetMapping("/filter")
    public ResponseEntity<?>filterStudents(
        @RequestParam(required = false) Integer age,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) Integer minAge,
        @RequestParam(required = false) Integer maxAge) {

        List<StudentResponse> students =
            studentService.filterStudents(age, email, minAge, maxAge);

             if (students.isEmpty()){
         return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("No students found");
    }
        return ResponseEntity.ok(students);
    }

    @GetMapping("/get")
    public ResponseEntity <StudentResponse> getStudent(@RequestParam Long id) {
        StudentResponse studentResp =studentService.getStudentById(id);
        if(studentResp == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentResp);
    }

     @GetMapping("/getall")
     public ResponseEntity <List<StudentResponse>> getAllStudent() {
        List <StudentResponse> studentList =studentService.getAllStudents();
        if(studentList.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable Long id,@Valid  
        @RequestBody StudentRequest studentReq) {
         StudentResponse studentResp=studentService.updateStudent(id,studentReq);

         if(studentResp==null){
            return ResponseEntity.notFound().build();
         }
        
        return ResponseEntity.ok(studentResp);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.ok("Student deleted successfully");
    }

    
    
}