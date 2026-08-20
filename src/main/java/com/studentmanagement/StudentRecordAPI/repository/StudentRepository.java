package com.studentmanagement.StudentRecordAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.studentmanagement.StudentRecordAPI.entity.Student;

public interface StudentRepository extends JpaRepository <Student,Long> {
   
}
