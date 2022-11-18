package com.hcl.springbootteachercrud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.springbootteachercrud.dto.Teacher;
import com.hcl.springbootteachercrud.repository.TeacherRepository;

@Service
public class TeacherService implements TeacherServiceInterface {
	@Autowired
	TeacherRepository teacherRepository;

	public Teacher saveTeacher(Teacher teacher) {
		teacherRepository.save(teacher);
		return teacher;

	}

	public Teacher getById(int id) {
		return teacherRepository.findById(id).get();
	}

	@Override
	public List<Teacher> getAll() {
		// TODO Auto-generated method stub
		List<Teacher> teachers = new ArrayList<Teacher>();
		teacherRepository.findAll().forEach(teacher1 -> teachers.add(teacher1));
		return teachers;
	}

	@Override
	public Teacher updateTeacher(Teacher teacher, int id) {
		// TODO Auto-generated method stub
		Teacher teacher1 = teacherRepository.getById(id);
		if (teacher1 != null) {
			teacherRepository.save(teacher);
		}
		return teacher;

	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Teacher teacher = teacherRepository.getById(id);
		if (teacher != null) {
			teacherRepository.deleteById(id);
		}

	}

	@Override
	public List<Teacher> saveAll(List<Teacher> teachers) {
		// TODO Auto-generated method stub
		
		return teacherRepository.saveAll(teachers);
	}

}
