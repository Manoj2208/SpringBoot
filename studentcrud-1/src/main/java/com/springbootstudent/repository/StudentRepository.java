package com.springbootstudent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootstudent.dto.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{

}
