package com.hcl.personadhar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.personadhar.dto.Person;
import com.hcl.personadhar.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	PersonRepository personRepository;

	@Override
	public Person savePerson(Person person) {
		// TODO Auto-generated method stub
		return personRepository.save(person);
	}

	@Override
	public Optional<Person> getPersonById(int id) {
		// TODO Auto-generated method stub
		return personRepository.findById(id);
	}

	@Override
	public List<Person> getAll() {
		// TODO Auto-generated method stub
		return personRepository.findAll();
	}

	@Override
	public Person updateById(Person person, int id) {
		// TODO Auto-generated method stub
		Optional<Person> p = personRepository.findById(id);
		if (p != null) {
			return personRepository.save(person);
		} else
			return null;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		personRepository.deleteById(id);
	}

}
