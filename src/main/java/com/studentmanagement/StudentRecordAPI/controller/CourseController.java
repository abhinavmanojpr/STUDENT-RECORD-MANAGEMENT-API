package com.studentmanagement.StudentRecordAPI.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import com.studentmanagement.StudentRecordAPI.entity.Course;
import com.studentmanagement.StudentRecordAPI.service.CourseService;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService=courseService;
    }

    @PostMapping("/create")
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @GetMapping("/getall")
    public ResponseEntity <List<Course>> getAllCourses() {
        List <Course> courseList=courseService.getAllCourses();
        if(courseList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(courseList);
    }

    @GetMapping("/get")
    public ResponseEntity <Course> getCourse(@RequestParam Long id) {
        Course courseResp= courseService.getCourse(id);
        if(courseResp==null){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(courseResp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course courseReq) {
        Course courseResp=courseService.updateCourse(id, courseReq);
        if(courseResp==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(courseResp);
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "Record Deleted";
    }


}
