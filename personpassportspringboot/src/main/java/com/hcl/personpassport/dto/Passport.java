package com.hcl.personadhar.dto;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Passport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int passportId;
	private String fatherName;
	private Date dateOfBirth;

}
