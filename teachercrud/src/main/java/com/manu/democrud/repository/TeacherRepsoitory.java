package com.manu.democrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manu.democrud.dto.Teacher;

@Repository
public interface TeacherRepsoitory extends JpaRepository<Teacher, Integer>{

}
