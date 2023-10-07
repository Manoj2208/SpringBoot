package com.alvas.ecommeerceapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductDto {

	private long productId;
	private int quantity;

}
