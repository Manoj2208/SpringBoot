package com.hcl.teachercrud.application.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.teachercrud.application.dto.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

	List<Teacher> findByFirstNameIgnoreCase(String name);

	List<Teacher> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String fname, String lname);

	List<Teacher> findByFirstNameIgnoreCaseOrLastNameIgnoreCase(String fname, String lname);

	List<Teacher> findByAgeGreaterThan(int age);

	List<Teacher> findBySalaryLessThanEqual(double salary);

	List<Teacher> findByLastNameIsNull();

	List<Teacher> findByFirstNameContainingOrderByFirstNameAsc(String fname);

	List<Teacher> findByDateOfJoinAfter(Date date);

	List<Teacher> findByDateOfJoinBetween(Date d1, Date d2);

	List<Teacher> findByFirstNameStartingWith(String fname);

}
