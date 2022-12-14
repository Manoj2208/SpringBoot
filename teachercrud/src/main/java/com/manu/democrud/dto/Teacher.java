package com.manu.democrud.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int teacherId;
	@NotBlank(message = "please provide name")
	private String teacherName;
	@NotBlank(message = "must provide subject name")
	private String subjectHandels;
	@Min(23)
	@Max(59)
	private int age;
	@Column(unique = true)
	@NotBlank(message = "must provide valid email")
	@Email(regexp ="[a-zA-Z0-9._-]+@[a-zA-Z]+.[a-zA-Z]{2,4}[.]{0,1}[a-zA-Z]{0,2}" ,message = "pattern is invalid")
	private String email;
	@NotNull(message = "mention salary")
	private float salary;

}
