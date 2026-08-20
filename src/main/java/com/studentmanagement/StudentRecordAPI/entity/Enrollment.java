package com.studentmanagement.StudentRecordAPI.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Table;


@Entity
@Table(name="enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private LocalDate enrollmentAt;
    @Column(nullable = false)
    private String status;
    @ManyToOne
    @JoinColumn(name = "Student_id",nullable=false,unique=true)
    private Student studentId;
    @ManyToOne
    @JoinColumn(name = "Course_id",nullable=false,unique=true)
    private Course courseId;
    
    @Column(nullable = false)
    private LocalDate enrollmentDate;


     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return studentId;
    }

    public void setStudent(Student student) {
        this.studentId = student;
    }

    public Course getCourse() {
        return courseId;
    }

    public void setCourse(Course course) {
        this.courseId = course;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

