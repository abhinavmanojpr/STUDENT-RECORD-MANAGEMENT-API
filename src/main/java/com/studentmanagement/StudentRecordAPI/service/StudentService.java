package com.studentmanagement.StudentRecordAPI.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;
import org.springframework.stereotype.Service;

import com.studentmanagement.StudentRecordAPI.entity.Student;
import com.studentmanagement.StudentRecordAPI.repository.StudentRepository;


@Service
public class StudentService {
    
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }
    
    public Student createStudent(Student studentReq){
      studentReq.setCreatedAt(LocalDateTime.now());
      studentReq.setUpdatedAt(LocalDateTime.now());

      Student studentResp=studentRepository.save(studentReq);
      return studentResp;
    }

    public Student getStudent(Long id){
       Optional <Student> studentResp= studentRepository.findById(id);

       if(studentResp.isPresent()){
        return studentResp.get();
       }

       return null;
    }

    public List<Student> getAllStudent(){
       List<Student> studentResp= studentRepository.findAll();
       return studentResp;
    }

    public Student updateStudent(Long id,Student studentReq){
       Optional <Student> studentResp= studentRepository.findById(id);

       if (studentResp.isEmpty()) {
        throw new RuntimeException("Student not found");
      }
      
      if (studentRepository.existsByEmailAndIdNot(studentReq.getEmail(), id)) {
        throw new RuntimeException("Email already exists");
      }


       Student studentToSave=studentResp.get();
       studentToSave.setFirstName(studentReq.getFirstName());
       studentToSave.setLastName(studentReq.getLastName());
       studentToSave.setAge(studentReq.getAge());
       studentToSave.setEmail(studentReq.getEmail());
       studentToSave.setAddress(studentReq.getAddress());

       return studentRepository.save(studentToSave);
    }

    public Boolean deleteStudent(Long id){
       Boolean studentResp= studentRepository.existsById(id);
       if(!studentResp) 
       return false;

       studentRepository.deleteById(id);
        return true;
    }
}