package com.hcl.personadhar.service;

import java.util.List;
import java.util.Optional;

import com.hcl.personadhar.dto.Person;

public interface PersonService {
	Person savePerson(Person person);

	Optional<Person> getPersonById(int id);

	List<Person> getAll();

	Person updateById(Person person, int id);

	void deleteById(int id);

}
