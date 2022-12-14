package com.manu.democrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manu.democrud.dto.Teacher;
import com.manu.democrud.exception.TeacherAlreadyExistException;
import com.manu.democrud.exception.TeacherNotFoundException;
import com.manu.democrud.repository.TeacherRepsoitory;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	TeacherRepsoitory teacherRepsoitory;

	@Override
	public Teacher saveTeacher(Teacher teacher) {
		Teacher existingTeacher=teacherRepsoitory.findById(teacher.getTeacherId()).orElse(null);
		if(existingTeacher==null) {
			return teacherRepsoitory.save(teacher);
		}
		else throw new TeacherAlreadyExistException("teacher already exist");
		

	}

	@Override
	public List<Teacher> getAllTeacher() {
		return teacherRepsoitory.findAll();
	}

	@Override
	public Optional<Teacher> getTeacherById(int id) {
		Optional<Teacher> teacher1 = teacherRepsoitory.findById(id);
		if (teacher1.isPresent()) {
			return teacher1;
		} else
			throw new TeacherNotFoundException("teacher with given id:" + id + " not present");

	}

	@Override
	public Optional<Teacher> deleteTeacherById(int id) {
		Optional<Teacher> teacher1 = teacherRepsoitory.findById(id);
		if (teacher1.isPresent()) {
			teacherRepsoitory.deleteById(id);
			return teacher1;
		} else
			throw new TeacherNotFoundException("teacher with given id:\"+id+\" not present");

	}

}
