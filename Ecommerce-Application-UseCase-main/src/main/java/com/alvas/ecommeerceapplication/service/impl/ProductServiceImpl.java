package com.alvas.ecommeerceapplication.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alvas.ecommeerceapplication.dto.ProductDto;
import com.alvas.ecommeerceapplication.entity.Product;
import com.alvas.ecommeerceapplication.exception.ProductNotFoundException;
import com.alvas.ecommeerceapplication.repository.ProductRepository;
import com.alvas.ecommeerceapplication.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepository;

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Override
	public Page<ProductDto> getProduct(String productName, int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Product> productPage = productRepository.findByProductNameContainingIgnoreCase(productName, pageable);
		if (productPage.isEmpty()) {
			logger.warn("product not available");
			throw new ProductNotFoundException("product not found");
		} else {
			logger.info("getting product based on pagenumber and pagesize");
			return productPage.map(p -> {
				ProductDto productDto = new ProductDto();
				BeanUtils.copyProperties(p, productDto);
				return productDto;
			});
		}

	}
}
