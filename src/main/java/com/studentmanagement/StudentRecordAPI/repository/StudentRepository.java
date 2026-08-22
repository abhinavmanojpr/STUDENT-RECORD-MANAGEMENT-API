package com.studentmanagement.StudentRecordAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import com.studentmanagement.StudentRecordAPI.entity.Student;

public interface StudentRepository extends JpaRepository <Student,Long> {

   boolean existsByEmail(String email);
   boolean existsByEmailAndIdNot(String email, Long id);

   @Query("""
    SELECT s FROM Student s
    WHERE LOWER(s.firstName) LIKE LOWER(CONCAT('%', :name, '%'))
       OR LOWER(s.lastName) LIKE LOWER(CONCAT('%', :name, '%'))
""")
   Page<Student> searchByName(
        @Param("name") String name,
        Pageable pageable
   );

   List<Student> findByAge(Integer age);


   List<Student> findByEmail(String email);

   List<Student> findByAgeGreaterThan(Integer age);

   List<Student> findByAgeLessThan(Integer age);

   List<Student> findByAgeBetween(Integer minAge, Integer maxAge);
}
