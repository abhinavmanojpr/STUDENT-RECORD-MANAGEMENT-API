package com.studentmanagement.StudentRecordAPI.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.studentmanagement.StudentRecordAPI.entity.Student;
import com.studentmanagement.StudentRecordAPI.service.StudentService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/students")
public class StudentController{

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }

    @PostMapping("/create")
    public  ResponseEntity<Student> createStudent(@RequestBody Student s){
        Student createdStudent=studentService.createStudent(s);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);

    }

    @GetMapping("/get")
    public ResponseEntity <Student> getStudent(@RequestParam Long id) {
        Student studentResp =studentService.getStudent(id);
        if(studentResp == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentResp);
    }

     @GetMapping("/getall")
    public ResponseEntity <List<Student>> getAllStudent() {
        List <Student> studentList =studentService.getAllStudent();
        if(studentList.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentReq) {
         Student studentResp=studentService.updateStudent(id,studentReq);

         if(studentResp==null){
            return ResponseEntity.notFound().build();
         }
        
        return ResponseEntity.ok(studentResp);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity <String> deleteStudent(@PathVariable Long id){
        Boolean isDeleted=studentService.deleteStudent(id);
        if(!isDeleted){
            return ResponseEntity.notFound().build();
         }
        
         return ResponseEntity.ok("Record Deleted");
    }
    
}