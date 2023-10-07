package com.alvas.ecommeerceapplication.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.alvas.ecommeerceapplication.dto.ProductDto;
import com.alvas.ecommeerceapplication.service.ProductService;

@ExtendWith(SpringExtension.class)
class ProductControllerTest {

	@InjectMocks
	ProductController productController;

	@Mock
	ProductService productService;

	@Test
	void getProductsTest() {
		String productName = "book";
		int pageNumber = 0;
		int pageSize = 2;
		List<ProductDto> productsList = new ArrayList<>();
		ProductDto p1 = new ProductDto();
		p1.setProductName("box");
		p1.setAvailableQuantity(100);
		p1.setPrice(50);

		ProductDto p2 = new ProductDto();
		p2.setProductName("book");
		p2.setAvailableQuantity(30);
		p2.setPrice(20);

		ProductDto p3 = new ProductDto();
		p3.setProductName("boost");
		p3.setAvailableQuantity(100);
		p3.setPrice(500);

		productsList.add(p2);

		Page<ProductDto> productsPage = new PageImpl<ProductDto>(productsList);
		when(productService.getProduct(anyString(), anyInt(), anyInt())).thenReturn(productsPage);

		ResponseEntity<List<ProductDto>> result = productController.getProducts(productName, pageNumber, pageSize);

		assertNotNull(result);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		List<ProductDto> dtos = result.getBody();
		assertNotNull(dtos);
		assertEquals(1, dtos.size());

	}
}
