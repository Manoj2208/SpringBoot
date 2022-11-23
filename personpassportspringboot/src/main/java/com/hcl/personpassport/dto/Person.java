package com.hcl.personadhar.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int personId;
	private String firstName;
	private String lastName;
	private int age;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "passportId")
	private Passport passport;

	
}
