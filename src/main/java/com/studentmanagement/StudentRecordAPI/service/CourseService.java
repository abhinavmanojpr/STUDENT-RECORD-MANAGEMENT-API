package com.studentmanagement.StudentRecordAPI.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import java.util.List;
import com.studentmanagement.StudentRecordAPI.entity.Course;
import com.studentmanagement.StudentRecordAPI.repository.CourseRepository;

@Service
public class CourseService {

     private  CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course){
        return courseRepository.save(course);
    }

    public Course getCourse(Long id){
       Optional <Course> courseResp= courseRepository.findById(id);

       if(courseResp.isPresent()){
        return courseResp.get();
       }

       return null;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course updateCourse(Long id, Course courseReq ){
        Optional <Course> courseResp=courseRepository.findById(id);
        if(courseResp.isEmpty()){
            throw new RuntimeException("Course not found");
        }

        Course courseToSave=courseResp.get();
        courseToSave.setCourseCode(courseReq.getCourseCode());
        courseToSave.setCourseName(courseReq.getCourseName());
        courseToSave.setCourseDescription(courseReq.getCourseDescription());
        courseToSave.setCredits(courseReq.getCredits());

        return courseRepository.save(courseToSave);
    }


    public void deleteCourse(Long id) {

        getCourse(id);

        courseRepository.deleteById(id);
    }
}


