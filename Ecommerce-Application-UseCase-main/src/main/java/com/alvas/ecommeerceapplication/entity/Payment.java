package com.alvas.ecommeerceapplication.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long paymentId;
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	private long walletId;
	@OneToOne(cascade = CascadeType.ALL)
	private Cart cart;
	@CreationTimestamp
	private LocalDate paymentDate;

}
