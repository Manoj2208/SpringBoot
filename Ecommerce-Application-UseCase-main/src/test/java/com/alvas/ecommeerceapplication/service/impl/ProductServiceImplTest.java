package com.alvas.ecommeerceapplication.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.alvas.ecommeerceapplication.dto.ProductDto;
import com.alvas.ecommeerceapplication.entity.Product;
import com.alvas.ecommeerceapplication.exception.ProductNotFoundException;
import com.alvas.ecommeerceapplication.repository.ProductRepository;

@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
	@InjectMocks
	ProductServiceImpl productServiceImpl;

	@Mock
	ProductRepository productRepository;

	
	
	@Test
	void getProductTest() {

		List<Product> productList = new ArrayList<>();
		Product p1 = new Product();
		p1.setProductId(1);
		p1.setProductName("box");
		p1.setAvailableQuantity(100);
		p1.setPrice(50);

		Product p2 = new Product();
		p2.setProductId(2);
		p2.setProductName("book");
		p2.setAvailableQuantity(30);
		p2.setPrice(20);

		Product p3 = new Product();
		p3.setProductId(3);
		p3.setProductName("boost");
		p3.setAvailableQuantity(100);
		p3.setPrice(500);

		productList.add(p1);
		productList.add(p2);
		productList.add(p3);

		Pageable pageable = PageRequest.of(0, 10);
		Page<Product> productPage = new PageImpl<>(productList, pageable, productList.size());

		when(productRepository.findByProductNameContainingIgnoreCase(any(), any())).thenReturn(productPage);

		Page<ProductDto> result = productServiceImpl.getProduct("product", 0, 10);

		Assertions.assertEquals(3, result.getTotalElements());
		Assertions.assertEquals(3, result.getContent().size());
		Assertions.assertEquals("box", result.getContent().get(0).getProductName());
		Assertions.assertEquals("book", result.getContent().get(1).getProductName());
	}



	@Test
	void getProductTestForNull() {

		Pageable pageable = PageRequest.of(0, 2);
		Page<Product> productPage = Page.empty(pageable);

		when(productRepository.findByProductNameContainingIgnoreCase(any(), any())).thenReturn(productPage);

		Assertions.assertThrows(ProductNotFoundException.class, () -> {
			productServiceImpl.getProduct(null, 0, 2);
		});

	}

	@Test
	 void testGetProductWithInvalidProductName() {

		String ProductName = "xyz";
		int pageNumber = 0;
		int pageSize = 2;
		Pageable pageable = PageRequest.of(0, 2);
		Page<Product> productPage = Page.empty(pageable);

		when(productRepository.findByProductNameContainingIgnoreCase(any(), any())).thenReturn(productPage);

		Exception exception = Assertions.assertThrows(ProductNotFoundException.class, () -> {
			productServiceImpl.getProduct(ProductName, pageNumber, pageSize);
		});

		assertEquals("product not found", exception.getMessage());

	}

}
