package com.alvas.ecommeerceapplication.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductResponse {
	
	private long  productId;
	
	private int quantity;
	
	private String productName;

}
