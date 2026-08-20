package com.studentmanagement.StudentRecordAPI.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private Integer age;
    
    @Column(unique = true)
    private String email;

    private String phone;
   
    private String address;
  
    private LocalDate dateOfBirth;
    private LocalDateTime  createdAt;
    private LocalDateTime updatedAt;

    
    public Long getId(){
        return id;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName=firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName=lastName;
    }

    public Integer getAge(){
        return age;
    }

     public void setAge(Integer age){
        this.age=age;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }
     public String getAddress(){
        return address;
    }
     public void setAddress(String address){
        this.address=address;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone=phone;
    }
    public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth= dateOfBirth;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this. createdAt=createdAt;
    }

     public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt){
        this.updatedAt=updatedAt;
    }


}