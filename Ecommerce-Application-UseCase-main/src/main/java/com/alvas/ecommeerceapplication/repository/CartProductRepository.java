package com.alvas.ecommeerceapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alvas.ecommeerceapplication.entity.CartProduct;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

}
