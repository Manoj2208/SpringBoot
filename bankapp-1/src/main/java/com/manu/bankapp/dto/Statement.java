package com.manu.bankapp.dto;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Statement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int statementId;
	private long accountNumber;
	private String status;
	@CreationTimestamp
	private LocalDate dateOfTransaction;
	

}
