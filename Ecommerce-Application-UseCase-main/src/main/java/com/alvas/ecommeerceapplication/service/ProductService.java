package com.alvas.ecommeerceapplication.service;

import org.springframework.data.domain.Page;

import com.alvas.ecommeerceapplication.dto.ProductDto;

public interface ProductService {
	Page<ProductDto> getProduct(String productName, int pageNumber, int pageSize);
	}
