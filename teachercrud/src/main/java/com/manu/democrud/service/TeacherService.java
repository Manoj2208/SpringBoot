package com.manu.democrud.service;

import java.util.List;
import java.util.Optional;

import com.manu.democrud.dto.Teacher;

public interface TeacherService {
	Teacher saveTeacher(Teacher teacher);

	List<Teacher> getAllTeacher();

	Optional<Teacher> getTeacherById(int id);

	Optional<Teacher> deleteTeacherById(int id);

}
