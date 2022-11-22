package com.hcl.teachercrud.application.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.teachercrud.application.dto.Teacher;
import com.hcl.teachercrud.application.service.TeacherServiceImpl;

@RestController
public class TeacherController {
	@Autowired
	TeacherServiceImpl teacherServiceImpl;

	@PostMapping("/save")
	public Teacher saveTeacher(@RequestBody Teacher teacher) {
		return teacherServiceImpl.saveTeacher(teacher);

	}

	@PostMapping("/saveall")
	public List<Teacher> saveAll(@RequestBody List<Teacher> teachers) {
		return teacherServiceImpl.saveAll(teachers);

	}

	@GetMapping("/getbyid")
	public Optional<Teacher> getTeacherById(@RequestParam int id) {
		return teacherServiceImpl.getById(id);
	}

	@GetMapping("/getall")
	public List<Teacher> getAllTeachers() {
		return teacherServiceImpl.getAll();
	}

	@PutMapping("/updatebyid")
	public Teacher updateTeacher(@RequestBody Teacher teacher, int id) {
		return teacherServiceImpl.updateTeacher(teacher, id);
	}

	@DeleteMapping("/deletebyid")
	public String deleteTeacher(int id) {
		teacherServiceImpl.deleteTeacher(id);
		return "teacher with id:" + id + " deleted successfully";
	}

	@GetMapping("/getbyfnameignorecase")
	public List<Teacher> getByFnameIgnoreCase(@RequestParam String fname) {
		return teacherServiceImpl.getByFirstNameIgnoreCase(fname);
	}

	@GetMapping("/getbyfnameandlname")
	public List<Teacher> getByFnameAndLname(@RequestParam String fname, @RequestParam String lname) {
		return teacherServiceImpl.getByFnameAndLname(fname, lname);
	}

	@GetMapping("/getbyfnameorlname")
	public List<Teacher> getByFnameOrLname(@RequestParam String fname, @RequestParam String lname) {
		return teacherServiceImpl.getByFnameOrLname(fname, lname);
	}

	@GetMapping("/getbyagemorethan")
	public List<Teacher> getByAgeMoreThan(@RequestParam int age) {
		return teacherServiceImpl.getByAgeMoreThan(age);
	}

	@GetMapping("/getbysallessthanequal")
	public List<Teacher> getBySalaryLessThanEqual(@RequestParam double salary) {
		return teacherServiceImpl.getBySalaryLessThanEqual(salary);
	}

	@GetMapping("/getbylastnamenull")
	public List<Teacher> getByLastNameIsNull() {
		return teacherServiceImpl.getByLastNameIsNull();
	}

	@GetMapping("/getbyfnamecontaingorderbyfname")
	public List<Teacher> getByFnameContainingOrderByFname(@RequestParam String fname) {
		return teacherServiceImpl.getByFNameContainingOrderByFname(fname);
	}

	@GetMapping("/getbydojafter")
	public List<Teacher> getByDojAfter(@RequestParam Date date) {
		return teacherServiceImpl.getByDojAfter(date);
	}

	@GetMapping("/getbydojbetween")
	public List<Teacher> getByDojBetween(@RequestParam Date d1, Date d2) {
		return teacherServiceImpl.getByDojBetween(d1, d2);
	}

	@GetMapping("/getbyfnamestartswith")
	public List<Teacher> getByFnameStartsWith(@RequestParam String fname) {
		return teacherServiceImpl.getByFnameStartsWith(fname);
	}
	
	@GetMapping("/getbyrangesortwithfname")
	public List<Teacher> getByRange(@RequestParam int pno,@RequestParam int psize){
		return teacherServiceImpl.getByRangeAndSorting(pno, psize);
	}

}
