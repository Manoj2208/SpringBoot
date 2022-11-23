package com.hcl.personadhar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.personadhar.dto.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{

}
