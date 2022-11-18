package com.hcl.springbootteachercrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.springbootteachercrud.dto.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

}
