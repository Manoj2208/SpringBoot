package com.alvas.ecommeerceapplication.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {


	@NotBlank
	private long cartId;
	@NotBlank
	private long walletId;


}
