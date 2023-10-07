package com.alvas.ecommeerceapplication.repository;


import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alvas.ecommeerceapplication.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByProductIdIn(List<Long> productId);


	Page<Product> findByProductNameContainingIgnoreCase(String productName, Pageable pageable);

}
