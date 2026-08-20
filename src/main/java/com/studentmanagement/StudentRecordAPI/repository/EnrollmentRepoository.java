package com.studentmanagement.StudentRecordAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.studentmanagement.StudentRecordAPI.entity.Enrollment;

public interface EnrollmentRepoository extends JpaRepository <Enrollment , Long> {

}
