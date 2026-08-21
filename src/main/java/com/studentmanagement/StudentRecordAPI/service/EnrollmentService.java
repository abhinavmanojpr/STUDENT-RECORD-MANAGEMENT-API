package com.studentmanagement.StudentRecordAPI.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.studentmanagement.StudentRecordAPI.dto.request.EnrollmentRequest;
import com.studentmanagement.StudentRecordAPI.dto.response.EnrollmentResponse;
import com.studentmanagement.StudentRecordAPI.entity.Course;
import com.studentmanagement.StudentRecordAPI.entity.Enrollment;
import com.studentmanagement.StudentRecordAPI.entity.Student;
import com.studentmanagement.StudentRecordAPI.exception.CourseNotFoundException;
import com.studentmanagement.StudentRecordAPI.exception.DuplicateEnrollmentException;
import com.studentmanagement.StudentRecordAPI.exception.EnrollmentNotFoundException;
import com.studentmanagement.StudentRecordAPI.exception.StudentNotFoundException;
import com.studentmanagement.StudentRecordAPI.repository.CourseRepository;
import com.studentmanagement.StudentRecordAPI.repository.EnrollmentRepository;
import com.studentmanagement.StudentRecordAPI.repository.StudentRepository;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(
            EnrollmentRepository enrollmentRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository) {

        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    // CREATE
    public EnrollmentResponse createEnrollment(
            EnrollmentRequest request) {

        if (enrollmentRepository.existsByStudent_IdAndCourse_Id(
        request.getStudentId(),
        request.getCourseId())) {

    throw new DuplicateEnrollmentException(
            "Student is already enrolled in this course");
        }

        Student student = studentRepository.findById(
                request.getStudentId()
        ).orElseThrow(() ->
                new StudentNotFoundException(
                        "Student not found with id: "
                        + request.getStudentId()
                )
        );

        Course course = courseRepository.findById(
                request.getCourseId()
        ).orElseThrow(() ->
                new CourseNotFoundException(
                        "Course not found with id: "
                        + request.getCourseId()
                )
        );

        Enrollment enrollment = new Enrollment();

        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setStatus(request.getStatus());
        enrollment.setEnrollmentDate(LocalDate.now());
    

        Enrollment savedEnrollment =
                enrollmentRepository.save(enrollment);

        return convertToResponse(savedEnrollment);
    }

    // GET ALL
    public List<EnrollmentResponse> getAllEnrollments() {

        List<Enrollment> enrollments =
                enrollmentRepository.findAll();

        List<EnrollmentResponse> responses =
                new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            responses.add(convertToResponse(enrollment));
        }

        return responses;
    }

    // GET BY ID
    public EnrollmentResponse getEnrollmentById(Long id) {

        Enrollment enrollment =
                enrollmentRepository.findById(id)
                .orElseThrow(() ->
                        new EnrollmentNotFoundException(
                                "Enrollment not found with id: "
                                + id
                        )
                );

        return convertToResponse(enrollment);
    }

    // UPDATE
    public EnrollmentResponse updateEnrollment(
            Long id,
            EnrollmentRequest request) {

        Enrollment enrollment =
                enrollmentRepository.findById(id)
                .orElseThrow(() ->
                        new EnrollmentNotFoundException(
                                "Enrollment not found with id: "
                                + id
                        )
                );

        Student student = studentRepository.findById(
                request.getStudentId()
        ).orElseThrow(() ->
                new StudentNotFoundException(
                        "Student not found with id: "
                        + request.getStudentId()
                )
        );

        Course course = courseRepository.findById(
                request.getCourseId()
        ).orElseThrow(() ->
                new CourseNotFoundException(
                        "Course not found with id: "
                        + request.getCourseId()
                )
        );

        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setStatus(request.getStatus());

        Enrollment updatedEnrollment =
                enrollmentRepository.save(enrollment);

        return convertToResponse(updatedEnrollment);
    }

    // DELETE
    public void deleteEnrollment(Long id) {

        Enrollment enrollment =
                enrollmentRepository.findById(id)
                .orElseThrow(() ->
                        new EnrollmentNotFoundException(
                                "Enrollment not found with id: "
                                + id
                        )
                );

        enrollmentRepository.delete(enrollment);
    }

    // ENTITY → RESPONSE DTO
    private EnrollmentResponse convertToResponse(
            Enrollment enrollment) {

        EnrollmentResponse response =
                new EnrollmentResponse();

        response.setId(enrollment.getId());

        response.setStudentId(
                enrollment.getStudent().getId()
        );

        response.setCourseId(
                enrollment.getCourse().getId()
        );

        response.setEnrollmentDate(
                enrollment.getEnrollmentDate()
        );


        response.setStatus(
                enrollment.getStatus()
        );

        return response;
    }
}