package com.alvas.ecommeerceapplication.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;
	private String productName;
	private int availableQuantity;
	private double price;

}
