package com.hcl.assessment.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Getter
@Setter
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	@NotNull(message = "first name is mandatory")
	private String firstName;
	private String lastName;
	@NotNull(message = "father name is required")
	private String fatherName;
	@Min(value = 18, message = "you are not elgible u must be >18")
	private int age;
	@NotNull(message = "please specify your gender")
	private String gender;
	@NotNull(message = "adhar id  required")
	private long aadharNo;
	@NotNull(message = "email mandatory")
	private String email;
	@NotNull(message = "phone number is required ")
	private long phoneNumber;
	@NotNull
	private String country;



}
