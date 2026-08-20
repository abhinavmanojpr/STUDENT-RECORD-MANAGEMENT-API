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
}
