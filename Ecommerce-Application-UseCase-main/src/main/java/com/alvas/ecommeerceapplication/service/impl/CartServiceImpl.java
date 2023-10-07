package com.alvas.ecommeerceapplication.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alvas.ecommeerceapplication.dto.CartDto;
import com.alvas.ecommeerceapplication.dto.CartProductDto;
import com.alvas.ecommeerceapplication.entity.Cart;
import com.alvas.ecommeerceapplication.entity.CartProduct;
import com.alvas.ecommeerceapplication.entity.Product;
import com.alvas.ecommeerceapplication.entity.User;
import com.alvas.ecommeerceapplication.exception.ProductNotFoundException;
import com.alvas.ecommeerceapplication.exception.UserNotFoundException;
import com.alvas.ecommeerceapplication.repository.CartRepository;
import com.alvas.ecommeerceapplication.repository.ProductRepository;
import com.alvas.ecommeerceapplication.repository.UserRepository;
import com.alvas.ecommeerceapplication.response.ApiResponse;
import com.alvas.ecommeerceapplication.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CartRepository cartRepository;
	private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

	@Override
	public ApiResponse addToCart(long userId, CartDto cartDto) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User with id:" + userId + " not found"));
		List<Product> products = productRepository
				.findByProductIdIn(cartDto.getProductDtos().stream().map(CartProductDto::getProductId).toList());

		Cart cart = new Cart();
		cart.setUser(user);
		List<CartProduct> cartProducts = cartDto.getProductDtos().stream().map(productDto -> {
			CartProduct cartProduct = new CartProduct();
			BeanUtils.copyProperties(productDto, cartProduct);
			Product product = products.stream().filter(p -> p.getProductId() == productDto.getProductId()).findFirst()
					.orElseThrow(() -> new ProductNotFoundException(
							"Product with id:" + productDto.getProductId() + " not found"));
			double productPrice = product.getPrice();
			double productTotalPrice = productPrice * productDto.getQuantity();
			cart.setTotalPrice(cart.getTotalPrice() + productTotalPrice);
			return cartProduct;
		}).toList();
		cart.setCartProducts(cartProducts);
		cart.setTotalQuantity(cartProducts.stream().mapToInt(CartProduct::getQuantity).sum());

		logger.info("Successfully persisted cart information");
		cartRepository.save(cart);

		return new ApiResponse("Successfully added products to cart", HttpStatus.CREATED);
	}

}
