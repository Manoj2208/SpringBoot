package com.hcl.springbootteachercrud.service;

import java.util.List;

import com.hcl.springbootteachercrud.dto.Teacher;

public interface TeacherServiceInterface {
	public Teacher saveTeacher(Teacher teacher);

	public Teacher getById(int id);

	public List<Teacher> getAll();

	public Teacher updateTeacher(Teacher teacher,int id);
	
	public void deleteById(int id);
	
	public List<Teacher> saveAll(List<Teacher> teachers);

}
