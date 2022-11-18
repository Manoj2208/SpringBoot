package com.springbootstudent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springbootstudent.dto.Student;
import com.springbootstudent.service.StudentService;

@RestController
public class StudentController {
	@Autowired
	StudentService studentService;

	@PostMapping("/save")
	public String saveStudent(@RequestBody Student student) {
		studentService.savestudent(student);
		return "student with id:"+ student.getId()+"saved successfully";

	}

	@GetMapping("/student/{studentid}")
	public Student getStudentById(@PathVariable("studentid") int id) {
		return studentService.getStudentById(id);
	}

	@GetMapping("/getall")
	public List<Student> getAllStudent() {
		return studentService.getAll();
	}
	@PutMapping("/update")
	public Student updateStudent(@RequestBody Student student) {
		studentService.updateStudent(student);
		return student;
	}
	@DeleteMapping("/delete/{id}")
	public String deleteStudent(@PathVariable("id") int id) {
		studentService.deleteStudent(id);
		return "student removed with id:"+id+"successfully";
	}
	

}
