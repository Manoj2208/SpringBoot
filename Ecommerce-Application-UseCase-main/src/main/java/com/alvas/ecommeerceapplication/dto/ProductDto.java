package com.alvas.ecommeerceapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

	private String productName;
	private int availableQuantity;
	private double price;

}
