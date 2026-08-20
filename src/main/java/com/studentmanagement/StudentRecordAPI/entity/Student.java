package com.studentmanagement.StudentRecordAPI.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message="Firstname is Required")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message="Lastname is Required")
    private String lastName;

    @Min(value=0 , message = "Age cannot be negative")
    private int age;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Column(unique = true)
    private String email;

    @Pattern(
    regexp = "^[0-9]{10}$",
    message = "Phone number must contain exactly 10 digits")
    private String phone;
    @NotBlank(message ="Address is required")
    private String address;
    @NotNull(message="Date of birth is required")
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

    public int getAge(){
        return age;
    }

     public void setAge(int age){
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