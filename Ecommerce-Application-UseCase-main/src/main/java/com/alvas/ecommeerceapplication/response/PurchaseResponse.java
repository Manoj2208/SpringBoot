package com.alvas.ecommeerceapplication.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponse {

	private long cartId;
	private double totalPrice;
	private int totalQuantity;
	private LocalDate paymentDate;

}
