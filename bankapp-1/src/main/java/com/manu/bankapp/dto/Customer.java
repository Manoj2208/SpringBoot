package com.manu.bankapp.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	private String firstName;
	private String lastName;
	private String fatherName;
	private int age;
	private String gender;
	@Column(unique = true)
	private long aadharNo;
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private long phoneNumber;
	private String country;

	@OneToOne
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "accountId")
	private Account account;

}
