package com.manu.democrud.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.manu.democrud.dto.Teacher;
import com.manu.democrud.exception.ErrorResponseAlternative;
import com.manu.democrud.exception.TeacherNotFoundException;
import com.manu.democrud.service.TeacherServiceImpl;

@RestController
public class TeacherController {
	@Autowired
	TeacherServiceImpl teacherServiceImpl;

	@PostMapping("/teachers")
	public Teacher saveTeacher(@Valid @RequestBody Teacher teacher) {
		return teacherServiceImpl.saveTeacher(teacher);

	}

	@GetMapping("/teacher")
	public List<Teacher> getallTeachers() {
		return teacherServiceImpl.getAllTeacher();
	}

	@GetMapping("/teacher/{id}")
	public Optional<Teacher> getById(@PathVariable int id) {
		return teacherServiceImpl.getTeacherById(id);

	}

	@DeleteMapping("/teacher/{id}")
	public Optional<Teacher> deleteById(@PathVariable int id) {
		return teacherServiceImpl.deleteTeacherById(id);
	}

}
