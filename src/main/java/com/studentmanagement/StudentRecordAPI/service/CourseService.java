package com.studentmanagement.StudentRecordAPI.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.studentmanagement.StudentRecordAPI.dto.request.CourseRequest;
import com.studentmanagement.StudentRecordAPI.dto.response.CourseResponse;
import com.studentmanagement.StudentRecordAPI.entity.Course;
import com.studentmanagement.StudentRecordAPI.exception.CourseNotFoundException;
import com.studentmanagement.StudentRecordAPI.repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // CREATE
    public CourseResponse createCourse(CourseRequest request) {

        Course course = new Course();

        course.setCourseCode(request.getCourseCode());
        course.setCourseName(request.getCourseName());
        course.setCourseDescription(request.getCourseDescription());
        course.setCredits(request.getCredits());

        Course savedCourse = courseRepository.save(course);

        return convertToResponse(savedCourse);
    }

    // GET ALL
    public List<CourseResponse> getAllCourses() {

        List<Course> courses = courseRepository.findAll();

        List<CourseResponse> responses = new ArrayList<>();

        for (Course course : courses) {
            responses.add(convertToResponse(course));
        }

        return responses;
    }

    // GET BY ID
    public CourseResponse getCourseById(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new CourseNotFoundException(
                                "Course not found with id: " + id
                        )
                );

        return convertToResponse(course);
    }

    // UPDATE
    public CourseResponse updateCourse(
            Long id,
            CourseRequest request) {

        Course courseToSave = courseRepository.findById(id)
                .orElseThrow(() ->
                        new CourseNotFoundException(
                                "Course not found with id: " + id
                        )
                );

        courseToSave.setCourseCode(request.getCourseCode());
        courseToSave.setCourseName(request.getCourseName());
        courseToSave.setCourseDescription(request.getCourseDescription());
        courseToSave.setCredits(request.getCredits());

        Course updatedCourse =
                courseRepository.save(courseToSave);

        return convertToResponse(updatedCourse);
    }

    // DELETE
    public void deleteCourse(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new CourseNotFoundException(
                                "Course not found with id: " + id
                        )
                );

        courseRepository.delete(course);
    }

    // ENTITY → RESPONSE DTO
    private CourseResponse convertToResponse(Course course) {

        CourseResponse response = new CourseResponse();

        response.setId(course.getId());
        response.setCourseCode(course.getCourseCode());
        response.setCourseName(course.getCourseName());
        response.setCourseDescription(course.getCourseDescription());
        response.setCredits(course.getCredits());

        return response;
    }
}