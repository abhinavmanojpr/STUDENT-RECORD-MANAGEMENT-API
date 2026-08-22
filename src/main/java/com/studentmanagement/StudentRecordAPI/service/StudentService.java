package com.studentmanagement.StudentRecordAPI.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.studentmanagement.StudentRecordAPI.dto.request.StudentRequest;
import com.studentmanagement.StudentRecordAPI.dto.response.StudentResponse;
import com.studentmanagement.StudentRecordAPI.entity.Student;
import com.studentmanagement.StudentRecordAPI.exception.EmailAlreadyExistsException;
import com.studentmanagement.StudentRecordAPI.exception.StudentNotFoundException;
import com.studentmanagement.StudentRecordAPI.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // CREATE
    public StudentResponse createStudent(StudentRequest request) {

        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "Email already exists"
            );
        }
      
        request.setCreatedAt(LocalDateTime.now());
        request.setUpdatedAt(LocalDateTime.now());
        Student student = new Student();

        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setAge(request.getAge());
        student.setAddress(request.getAddress());

        Student savedStudent = studentRepository.save(student);

        return convertToResponse(savedStudent);
    }


    
    public Page<StudentResponse> getAllStudents(
        Pageable pageable) {

    Page<Student> students =
            studentRepository.findAll(pageable);

    return students.map(this::convertToResponse);
}


   
    public StudentResponse getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: " + id
                        )
                );

        return convertToResponse(student);
    }


   
    public StudentResponse updateStudent(
            Long id,
            StudentRequest request) {

        Student studentToSave = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: " + id
                        )
                );

        if (studentRepository.existsByEmailAndIdNot(
                request.getEmail(), id)) {

            throw new EmailAlreadyExistsException(
                    "Email already exists"
            );
        }
        request.setUpdatedAt(LocalDateTime.now());
        studentToSave.setFirstName(request.getFirstName());
        studentToSave.setLastName(request.getLastName());
        studentToSave.setEmail(request.getEmail());
        studentToSave.setPhone(request.getPhone());
        studentToSave.setDateOfBirth(request.getDateOfBirth());
        studentToSave.setAge(request.getAge());
        studentToSave.setAddress(request.getAddress());

        Student updatedStudent =
                studentRepository.save(studentToSave);

        return convertToResponse(updatedStudent);
    }

        public Page<StudentResponse> getStudents(Pageable pageable) {

        Page<Student> students = studentRepository.findAll(pageable);

                return students.map(this::convertToResponse);
        }
    
    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: " + id
                        )
                );

        studentRepository.delete(student);
    }

        public Page<StudentResponse> searchStudents(
                String name,Pageable pageable) {

         Page<Student> students =
                studentRepository.searchByName(name, pageable);

                return students.map(this::convertToResponse);
        }

       public List<StudentResponse> filterStudents(Integer age,String email,
                Integer minAge,
                Integer maxAge) {

                if (age != null) {
                        return studentRepository.findByAge(age)
                                .stream()
                                .map(this::convertToResponse)
                                .toList();
                        }

                if (email != null) {
                        return studentRepository.findByEmail(email)
                                .stream()
                                .map(this::convertToResponse)
                                .toList();
                        }


                 if (minAge != null && maxAge == null) {
                        return studentRepository.findByAgeGreaterThan(minAge)
                                .stream()
                                .map(this::convertToResponse)
                                .toList();
                        }


                if (maxAge != null && minAge == null) {
                        return studentRepository.findByAgeLessThan(maxAge)
                                .stream()
                                .map(this::convertToResponse)
                                .toList();
                        }

    
                if (minAge != null && maxAge != null) {
                        return studentRepository.findByAgeBetween(minAge, maxAge)
                                .stream()
                                .map(this::convertToResponse)
                                .toList();
                        }

            return List.of();
        }

    // ENTITY → RESPONSE DTO
    private StudentResponse convertToResponse(Student student) {

        StudentResponse response = new StudentResponse();

        response.setId(student.getId());
        response.setFirstName(student.getFirstName());
        response.setLastName(student.getLastName());
        response.setEmail(student.getEmail());
        response.setPhone(student.getPhone());
        response.setDateOfBirth(student.getDateOfBirth());
        response.setAge(student.getAge());
        response.setAddress(student.getAddress());
        response.setCreatedAt(student.getCreatedAt());
        response.setUpdatedAt(student.getUpdatedAt());

        return response;
    }
}