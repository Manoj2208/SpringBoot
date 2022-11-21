package com.hcl.springbootteachercrud.controller;

import java.util.List;
import java.util.Optional;

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
	public Teacher saveTeacher(@RequestBody Teacher teacher) {
		teacherService.saveTeacher(teacher);
		return teacher;

	}

	@PostMapping("/saveall")
	public List<Teacher> saveall(@RequestBody List<Teacher> teachers) {
		return teacherService.saveAll(teachers);

	}

	@GetMapping("/get/{id}")
	public Optional<Teacher> getById(@PathVariable("id") int id) {
		return teacherService.getById(id);
	}

	@GetMapping("/getall")
	public List<Teacher> getAll() {
		return teacherService.getAll();

	}

	@PutMapping("/update/{id}")
	public Teacher updateTeacher(@RequestBody Teacher teacher, @PathVariable("id") int id) {
		return teacherService.updateTeacher(teacher, id);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteTeacher(@PathVariable("id") int id) {
		teacherService.deleteById(id);
		return "teacher with id: " + id + "deleted sucessfully";
	}

	@GetMapping("/getbyname/{name}")
	public List<Teacher> getByName(@PathVariable("name") String name) {
		return teacherService.getByName(name);

	}

	@GetMapping("/getbysalarygt/{salary}")
	public List<Teacher> getBySalaryGT(@PathVariable("salary") double salary) {
		return teacherService.getBySalaryGreaterThan(salary);

	}

	@GetMapping("/getbynamelike/{nam}")
	public List<Teacher> getByNameLike(@PathVariable("nam") String nam) {
		return teacherService.getByNameLike(nam);

	}

	@GetMapping("/getbynamenot/{name}")
	public List<Teacher> getByNameNotLike(@PathVariable("name") String name) {
		return teacherService.getByNameNotLike(name);
	}

	@GetMapping("/getbysalarylt/{salary}")
	public List<Teacher> getBySalaryLT(@PathVariable("salary") double salary) {
		return teacherService.getBySalaryLessThan(salary);
	}

	@GetMapping("gtbynameandsal/{name}/{salary}")
	public List<Teacher> getByNameAndSalary(@PathVariable("name") String name, @PathVariable("salary") double salary) {
		return teacherService.getByNameAndSalary(name, salary);
	}

}
