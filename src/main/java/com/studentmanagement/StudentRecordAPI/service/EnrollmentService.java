package com.studentmanagement.StudentRecordAPI.service;

import org.springframework.stereotype.Service;

import com.studentmanagement.StudentRecordAPI.entity.Course;
import com.studentmanagement.StudentRecordAPI.entity.Enrollment;
import com.studentmanagement.StudentRecordAPI.entity.Student;
import com.studentmanagement.StudentRecordAPI.repository.CourseRepository;
import com.studentmanagement.StudentRecordAPI.repository.StudentRepository;
import com.studentmanagement.StudentRecordAPI.repository.EnrollmentRepository;

import java.time.LocalDate;

import java.util.List;

@Service
public class EnrollmentService {
    
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,StudentRepository studentRepository,CourseRepository courseRepository){
        this.enrollmentRepository=enrollmentRepository;
        this.studentRepository=studentRepository;
        this.courseRepository=courseRepository;
    }

     public Enrollment createEnrollment(Long studentId, Long courseId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

       Enrollment enrollment = new Enrollment();

        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDate.now());
        enrollment.setStatus("ACTIVE");

        return enrollmentRepository.save(enrollment);
    }


     public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment getEnrollmentById(Long id) {

        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
    }

    public Enrollment updateEnrollment(
        Long id,
        Long studentId,
        Long courseId,
        String status) {

        Enrollment enrollment = getEnrollmentById(id);

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setStatus(status);

        return enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollment(Long id) {
        getEnrollmentById(id);
        enrollmentRepository.deleteById(id);
    }

} 
