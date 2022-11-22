package com.hcl.teachercrud.application.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.hcl.teachercrud.application.dto.Teacher;

public interface TeacherService {
	Teacher saveTeacher(Teacher teacher);

	List<Teacher> saveAll(List<Teacher> teachers);

	Optional<Teacher> getById(int id);

	List<Teacher> getAll();

	Teacher updateTeacher(Teacher teacher, int id);

	void deleteTeacher(int id);

	List<Teacher> getByFirstNameIgnoreCase(String name);

	List<Teacher> getByFnameAndLname(String fname, String lname);

	List<Teacher> getByFnameOrLname(String fname, String lname);

	List<Teacher> getByAgeMoreThan(int age);

	List<Teacher> getBySalaryLessThanEqual(double salary);

	List<Teacher> getByLastNameIsNull();

	List<Teacher> getByFNameContainingOrderByFname(String fname);

	List<Teacher> getByDojAfter(Date date);

	List<Teacher> getByDojBetween(Date d1, Date d2);

	List<Teacher> getByFnameStartsWith(String fname);

	List<Teacher> getByRangeAndSorting(int pno, int psize);

	List<Teacher> getByAgeIn(List<Integer> ages);
	
	List<Teacher> getByAgeNotIn(List<Integer> ages);
	
	

}
