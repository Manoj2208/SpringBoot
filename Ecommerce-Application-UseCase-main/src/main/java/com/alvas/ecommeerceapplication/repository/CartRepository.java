package com.alvas.ecommeerceapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alvas.ecommeerceapplication.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	Cart findByCartIdAndUserUserId(long cartId,long userId);
}
