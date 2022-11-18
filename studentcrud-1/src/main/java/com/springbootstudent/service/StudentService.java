package com.springbootstudent.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootstudent.dto.Student;
import com.springbootstudent.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	StudentRepository repository;
	public void savestudent(Student student) {
		repository.save(student);
	}
	public Student getStudentById(int id) {
		return repository.findById(id).get();
		 
	}
	public List<Student> getAll(){
		List<Student> students=new ArrayList<Student>();
		repository.findAll().forEach(student1->students.add(student1));
		return students;
	}
	public void updateStudent(Student student) {
		repository.save(student);
	}
	public void deleteStudent(int id) {
		repository.deleteById(id);
	}

}
