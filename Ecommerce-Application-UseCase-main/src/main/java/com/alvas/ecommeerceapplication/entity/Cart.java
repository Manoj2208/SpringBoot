package com.alvas.ecommeerceapplication.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartId;
	private double totalPrice;
	private int totalQuantity;
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	@OneToMany(cascade = CascadeType.ALL)
	private List<CartProduct> cartProducts;
}
