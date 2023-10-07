package com.alvas.ecommeerceapplication.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.alvas.ecommeerceapplication.dto.CartDto;
import com.alvas.ecommeerceapplication.dto.CartProductDto;
import com.alvas.ecommeerceapplication.response.ApiResponse;
import com.alvas.ecommeerceapplication.service.CartService;

@ExtendWith(SpringExtension.class)
class CartControllerTest {
	@Mock
	private CartService cartService;

	@InjectMocks
	private CartController cartController;

	@Test
	void testAddToCart() throws Exception {
		long userId = 1L;
		List<CartProductDto> productDtos = Arrays.asList(new CartProductDto(1L, 2), new CartProductDto(2L, 3));
		CartDto cartDto = new CartDto(productDtos);
		ApiResponse expectedResponse = new ApiResponse("Successfully Added product to cart", HttpStatus.CREATED);
		Mockito.when(cartService.addToCart(userId, cartDto)).thenReturn(expectedResponse);

		assertEquals(HttpStatus.CREATED, cartService.addToCart(userId, cartDto).getStatus());

	}
}
