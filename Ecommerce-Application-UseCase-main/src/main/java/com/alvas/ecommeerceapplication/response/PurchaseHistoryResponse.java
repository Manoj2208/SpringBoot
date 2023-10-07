package com.alvas.ecommeerceapplication.response;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseHistoryResponse {

	private List<CartProductResponse> cartProduct;
	private LocalDate paymentDate;
	private Double totalPrice;
	private long walletId;

}
