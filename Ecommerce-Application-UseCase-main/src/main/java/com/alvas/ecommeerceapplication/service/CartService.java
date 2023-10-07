package com.alvas.ecommeerceapplication.service;

import com.alvas.ecommeerceapplication.dto.CartDto;
import com.alvas.ecommeerceapplication.response.ApiResponse;

public interface CartService {
	ApiResponse addToCart(long userId, CartDto cartDto);
}
