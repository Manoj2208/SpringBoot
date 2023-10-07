package com.alvas.ecommeerceapplication.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long walletId;
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	private LocalDate expiryDate;
	private double balance;
	private String walletType;

}
