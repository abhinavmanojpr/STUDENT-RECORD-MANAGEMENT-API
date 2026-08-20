package com.studentmanagement.StudentRecordAPI.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name="course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false , unique = true )
    private String courseName;
    @Column(nullable = false,unique = true)
    private Integer courseCode;
    @Column(nullable = false)
    private String courseDescription;
    @Column(nullable = true) 
    private Integer credits;

    public Long getId(){
        return id;
    }

    public String getCourseName(){
        return courseName;
    }

    public void setCourseName(String courseName){
        this.courseName = courseName;
    }

    public String getCourseDescription(){
        return courseDescription;
    }

    public int getCourseCode(){
        return courseCode;
    }

    public void setCourseCode(Integer courseCode){
        this.courseCode = courseCode;
    }

    public void setCourseDescription(String courseDescription){
        this.courseDescription = courseDescription;
    }

    public Integer getCredits(){
        return credits;
    }

    public void setCredits(Integer credits){
        this.credits = credits;
    }
}
