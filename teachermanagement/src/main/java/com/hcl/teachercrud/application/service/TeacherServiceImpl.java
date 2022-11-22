package com.hcl.teachercrud.application.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.hcl.teachercrud.application.dto.Teacher;
import com.hcl.teachercrud.application.repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	TeacherRepository teacherRepository;

	@Override
	public Teacher saveTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherRepository.save(teacher);
	}

	@Override
	public List<Teacher> saveAll(List<Teacher> teachers) {
		// TODO Auto-generated method stub
		return teacherRepository.saveAll(teachers);
	}

	@Override
	public Optional<Teacher> getById(int id) {
		// TODO Auto-generated method stub
		return teacherRepository.findById(id);
	}

	@Override
	public List<Teacher> getAll() {
		// TODO Auto-generated method stub
		return teacherRepository.findAll();
	}

	@Override
	public Teacher updateTeacher(Teacher teacher, int id) {
		// TODO Auto-generated method stub
		Optional<Teacher> teacher1 = teacherRepository.findById(id);
		if (teacher1 != null) {
			return teacherRepository.save(teacher);
		} else
			return null;

	}

	@Override
	public void deleteTeacher(int id) {
		// TODO Auto-generated method stub
		Optional<Teacher> teacher = teacherRepository.findById(id);
		if (teacher != null) {
			teacherRepository.deleteById(id);
		}

	}

	@Override
	public List<Teacher> getByFirstNameIgnoreCase(String name) {
		// TODO Auto-generated method stub
		return teacherRepository.findByFirstNameIgnoreCase(name);
	}

	@Override
	public List<Teacher> getByFnameAndLname(String fname, String lname) {
		// TODO Auto-generated method stub
		return teacherRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(fname, lname);
	}

	@Override
	public List<Teacher> getByFnameOrLname(String fname, String lname) {
		// TODO Auto-generated method stub
		return teacherRepository.findByFirstNameIgnoreCaseOrLastNameIgnoreCase(fname, lname);
	}

	@Override
	public List<Teacher> getByAgeMoreThan(int age) {
		// TODO Auto-generated method stub
		return teacherRepository.findByAgeGreaterThan(age);
	}

	@Override
	public List<Teacher> getBySalaryLessThanEqual(double salary) {
		// TODO Auto-generated method stub
		return teacherRepository.findBySalaryLessThanEqual(salary);
	}

	@Override
	public List<Teacher> getByLastNameIsNull() {
		// TODO Auto-generated method stub
		return teacherRepository.findByLastNameIsNull();
	}

	@Override
	public List<Teacher> getByFNameContainingOrderByFname(String fname) {
		// TODO Auto-generated method stub
		return teacherRepository.findByFirstNameContainingOrderByFirstNameAsc(fname);
	}

	@Override
	public List<Teacher> getByDojAfter(Date date) {
		// TODO Auto-generated method stub
		return teacherRepository.findByDateOfJoinAfter(date);
	}

	@Override
	public List<Teacher> getByDojBetween(Date d1, Date d2) {
		// TODO Auto-generated method stub
		return teacherRepository.findByDateOfJoinBetween(d1, d2);
	}

	@Override
	public List<Teacher> getByFnameStartsWith(String fname) {
		// TODO Auto-generated method stub
		return teacherRepository.findByFirstNameStartingWith(fname);
	}

	@Override
	public List<Teacher> getByRangeAndSorting(int pno, int psize) {
		// TODO Auto-generated method stub
		Pageable pageable=PageRequest.of(pno, psize,Sort.by(Direction.ASC, "firstName"));
		return teacherRepository.findAll(pageable).getContent();
	}

}
