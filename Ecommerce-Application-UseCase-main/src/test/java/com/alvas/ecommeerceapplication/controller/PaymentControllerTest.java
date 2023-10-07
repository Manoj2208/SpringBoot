package com.alvas.ecommeerceapplication.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.alvas.ecommeerceapplication.dto.PaymentDto;
import com.alvas.ecommeerceapplication.response.ApiResponse;
import com.alvas.ecommeerceapplication.service.PaymentService;

@ExtendWith(SpringExtension.class)
class PaymentControllerTest {

	@Mock
	PaymentService paymentService;

	@InjectMocks
	PaymentController paymentController;

	@Test
	void testPayment() {
		long userId = 1L;
		PaymentDto paymentDto = new PaymentDto(1L, 1L);
		ApiResponse apiResponse = new ApiResponse("purchase successfull", HttpStatus.CREATED);
		Mockito.when(paymentService.payment(userId, paymentDto)).thenReturn(apiResponse);
		ResponseEntity<ApiResponse> response = paymentController.payment(userId, paymentDto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());

	}

}
