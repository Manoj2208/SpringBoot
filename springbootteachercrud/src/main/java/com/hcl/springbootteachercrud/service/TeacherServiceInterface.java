package com.hcl.springbootteachercrud.service;

import java.util.List;
import java.util.Optional;

import com.hcl.springbootteachercrud.dto.Teacher;

public interface TeacherServiceInterface {
	Teacher saveTeacher(Teacher teacher);

	Optional<Teacher> getById(int id);

	List<Teacher> getAll();

	Teacher updateTeacher(Teacher teacher, int id);

	void deleteById(int id);

	List<Teacher> saveAll(List<Teacher> teachers);

	List<Teacher> getByName(String name);

	List<Teacher> getBySalaryGreaterThan(double salary);

	List<Teacher> getByNameLike(String nam);

	List<Teacher> getByNameNotLike(String name);

	List<Teacher> getBySalaryLessThan(double salary);
	
	List<Teacher> getByNameAndSalary(String name,double salary);

}
