package com.alvas.ecommeerceapplication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alvas.ecommeerceapplication.dto.CartDto;
import com.alvas.ecommeerceapplication.response.ApiResponse;
import com.alvas.ecommeerceapplication.service.CartService;

@RestController
@RequestMapping("/user-carts")
public class CartController {
	@Autowired
	private CartService cartService;

	@PostMapping
	public ResponseEntity<ApiResponse> addToCart(@RequestHeader("userId") long userId,
			@RequestBody @Valid CartDto cartDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addToCart(userId, cartDto));
	}
}
