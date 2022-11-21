package com.hcl.springbootteachercrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.springbootteachercrud.dto.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

	List<Teacher> findByName(String name);

	List<Teacher> findBySalaryGreaterThan(double salary);

	@Query(" from Teacher where name LIKE ?1%")
	List<Teacher> findByNameLike(String nam);

	List<Teacher> findByNameNot(String name);

	List<Teacher> findBySalaryLessThan(double salary);
	
	List<Teacher> findByNameAndSalary(String name,double salary);

}
