package com.hcl.personadhar.controller;

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

import com.hcl.personadhar.dto.Person;
import com.hcl.personadhar.service.PersonServiceImpl;

@RestController
public class PersonController {
	@Autowired
	PersonServiceImpl personServiceImpl;

	@PostMapping("/savepersonnpass")
	public Person savePersonAndPassport(@RequestBody Person person) {
		return personServiceImpl.savePerson(person);

	}

	@GetMapping("/getpersonandpassportbyid")
	public Optional<Person> getPersonAndPassportById(@RequestParam int id) {
		return personServiceImpl.getPersonById(id);
	}

	@GetMapping("/getallpersonpassport")
	public List<Person> getAll() {
		return personServiceImpl.getAll();
	}

	@PutMapping("/updatebypersonid")
	public Person updatePersonById(@RequestBody Person person, int id) {
		return personServiceImpl.updateById(person, id);

	}
	
	@DeleteMapping("/deletebyperson")
	public String deletePersonAndPassport(@RequestParam int id) {
		personServiceImpl.deleteById(id);
		return "person with :"+id+" deleted successfully";
	}

}
