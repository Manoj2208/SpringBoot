package com.hcl.springbootteachercrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.springbootteachercrud.dto.Teacher;
import com.hcl.springbootteachercrud.service.TeacherService;

@RestController
public class TeacherController {
	@Autowired
	TeacherService teacherService;
	
	@PostMapping("/saveteacher")
	public Teacher saveTeacher(@RequestBody Teacher teacher){
		teacherService.saveTeacher(teacher);
		return teacher;
		
	}
	@PostMapping("/saveall")
	public List<Teacher> saveall(@RequestBody List<Teacher> teachers){
		return teacherService.saveAll(teachers);
		
	}
	@GetMapping("/get/{id}")
	public Teacher getById(@PathVariable("id") int id){
		return teacherService.getById(id);
	}
	
	@GetMapping("/getall")
	public List<Teacher> getAll(){
		return teacherService.getAll();
		
	}
	
	@PutMapping("/update/{id}")
	public Teacher updateTeacher(@RequestBody Teacher teacher,@PathVariable("id") int id){
		return teacherService.updateTeacher(teacher, id);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteTeacher(@PathVariable("id") int id){
		teacherService.deleteById(id);
		return "teacher with id: "+id+"deleted sucessfully";
	}
	

}
