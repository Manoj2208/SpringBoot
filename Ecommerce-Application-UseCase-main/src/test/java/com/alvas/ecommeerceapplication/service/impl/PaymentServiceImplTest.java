package com.alvas.ecommeerceapplication.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.alvas.ecommeerceapplication.dto.PaymentDto;
import com.alvas.ecommeerceapplication.entity.Cart;
import com.alvas.ecommeerceapplication.entity.CartProduct;
import com.alvas.ecommeerceapplication.entity.Payment;
import com.alvas.ecommeerceapplication.entity.Product;
import com.alvas.ecommeerceapplication.entity.User;
import com.alvas.ecommeerceapplication.entity.Wallet;
import com.alvas.ecommeerceapplication.exception.CartNotFoundException;
import com.alvas.ecommeerceapplication.exception.InsufficientFundsException;
import com.alvas.ecommeerceapplication.exception.NoPurchaseHistoryFoundException;
import com.alvas.ecommeerceapplication.exception.RequestedQuantityNotAvailableException;
import com.alvas.ecommeerceapplication.exception.UserIdNotFoundException;
import com.alvas.ecommeerceapplication.exception.UserNotFoundException;
import com.alvas.ecommeerceapplication.exception.WalletExpiredException;
import com.alvas.ecommeerceapplication.exception.WalletNotFoundException;
import com.alvas.ecommeerceapplication.repository.CartRepository;
import com.alvas.ecommeerceapplication.repository.PaymentRepository;
import com.alvas.ecommeerceapplication.repository.ProductRepository;
import com.alvas.ecommeerceapplication.repository.UserRepository;
import com.alvas.ecommeerceapplication.repository.WalletRepository;
import com.alvas.ecommeerceapplication.response.ApiResponse;
import com.alvas.ecommeerceapplication.response.CartProductResponse;
import com.alvas.ecommeerceapplication.response.PurchaseHistoryResponse;

@ExtendWith(SpringExtension.class)
class PaymentServiceImplTest {

	@Mock
	ProductRepository productRepository;

	@Mock
	CartRepository cartRepository;

	@Mock
	WalletRepository walletRepository;

	@Mock
	PaymentRepository paymentRepository;

	@Mock
	UserRepository userRepository;

	@InjectMocks
	PaymentServiceImpl paymentServiceImpl;

	@Test
	void testGetUserPurchasesForMonth_UserIdNotFoundException() {

		Long userId = 1L;
		LocalDate monthDate = LocalDate.now();
		int pageNumber = 0;
		int pageSize = 10;

		Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

		Assertions.assertThrows(UserIdNotFoundException.class, () -> {
			paymentServiceImpl.getUserPurchasesForMonth(userId, monthDate, pageNumber, pageSize);
		});
	}

	@Test
	void testGetUserPurchasesForMonth_NoPurchaseHistoryFoundException() {

		Long userId = 1L;
		LocalDate monthDate = LocalDate.of(2023, 3, 1);
		int pageNumber = 0;
		int pageSize = 10;

		Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
		Mockito.when(paymentRepository.findByUserUserIdAndPaymentDateBetween(userId, monthDate.withDayOfMonth(1),
				monthDate.withDayOfMonth(monthDate.lengthOfMonth()),
				PageRequest.of(pageNumber, pageSize, Sort.by("paymentDate").descending())))
				.thenReturn(new PageImpl<>(Collections.emptyList()));

		Assertions.assertThrows(NoPurchaseHistoryFoundException.class, () -> {
			paymentServiceImpl.getUserPurchasesForMonth(userId, monthDate, pageNumber, pageSize);
		});
	}

	@Test
	void testGetUserPurchasesForMonth() {

		Long userId = 1L;
		LocalDate monthDate = LocalDate.of(2023, 3, 1);
		int pageNumber = 0;
		int pageSize = 10;

		User user = new User();
		user.setUserId(userId);

		CartProduct cartProduct = new CartProduct();
		cartProduct.setProductId(1L);
		cartProduct.setQuantity(2);

		Product product = new Product();
		product.setProductId(1L);
		product.setProductName("Bag");

		Cart cart = new Cart();
		cart.setTotalPrice(10.0);
		cart.setCartProducts(Arrays.asList(cartProduct));

		Payment payment = new Payment();
		payment.setPaymentDate(LocalDate.now());
		payment.setCart(cart);
		payment.setWalletId(1L);
		payment.setUser(user);

		Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
		Mockito.when(paymentRepository.findByUserUserIdAndPaymentDateBetween(userId, monthDate.withDayOfMonth(1),
				monthDate.withDayOfMonth(monthDate.lengthOfMonth()),
				PageRequest.of(pageNumber, pageSize, Sort.by("paymentDate").descending())))
				.thenReturn(new PageImpl<>(Collections.singletonList(payment)));
		Mockito.when(productRepository.findById(cartProduct.getProductId())).thenReturn(Optional.of(product));

		List<PurchaseHistoryResponse> result = paymentServiceImpl.getUserPurchasesForMonth(userId, monthDate,
				pageNumber, pageSize);

		Assertions.assertEquals(1, result.size());
		PurchaseHistoryResponse purchaseHistoryResponse = result.get(0);
		Assertions.assertEquals(LocalDate.now(), purchaseHistoryResponse.getPaymentDate());
		Assertions.assertEquals(10.0, purchaseHistoryResponse.getTotalPrice());
		Assertions.assertEquals(1L, purchaseHistoryResponse.getWalletId());
		List<CartProductResponse> cartProducts = purchaseHistoryResponse.getCartProduct();
		Assertions.assertEquals(1, cartProducts.size());
		CartProductResponse cartProductResponse = cartProducts.get(0);
		Assertions.assertEquals(1L, cartProductResponse.getProductId());
		Assertions.assertEquals(2, cartProductResponse.getQuantity());
		Assertions.assertEquals("Bag", cartProductResponse.getProductName());
	}

	@Test
	void testPaymentSuccess() {
		Product product = new Product(1L, "Bat", 45, 665.45);
		Product product2 = new Product(2L, "Basket ball", 50, 1000);

		User user = new User(12L, "Manoj", "manu@gmail.com");

		Wallet wallet = new Wallet(1L, user, LocalDate.now().plusDays(5), 30000, "Paytm");

		CartProduct cartProduct = new CartProduct(123L, 1L, 3);
		CartProduct cartProduct2 = new CartProduct(234L, 2L, 5);

		List<CartProduct> cartProducts = new ArrayList<>();
		cartProducts.add(cartProduct);
		cartProducts.add(cartProduct2);

		Cart cart = new Cart(1L, 6996.35, 8, user, cartProducts);
		PaymentDto paymentDto = new PaymentDto(1L, 1L);
		Payment payment = new Payment(1L, user, 1L, cart, LocalDate.now());

		Map<Long, Product> productMap = new HashMap<Long, Product>() {
			{
				put(product.getProductId(), product);
				put(product2.getProductId(), product2);
			}
		};
		List<Product> productList = new ArrayList<>(productMap.values());

		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
		Mockito.when(cartRepository.findById(paymentDto.getCartId())).thenReturn(Optional.of(cart));
		Mockito.when(walletRepository.findById(paymentDto.getWalletId())).thenReturn(Optional.of(wallet));
		Mockito.when(cartRepository.findByCartIdAndUserUserId(paymentDto.getCartId(), user.getUserId())).thenReturn(cart);
		Mockito.when(walletRepository.findByWalletIdAndUserUserId(paymentDto.getWalletId(), user.getUserId())).thenReturn(wallet);
		Mockito.when(productRepository.findAllById(anyList())).thenReturn(productList);
		Mockito.when(productRepository.saveAll(productMap.values())).thenReturn(productList);
		Mockito.when(walletRepository.save(wallet)).thenReturn(wallet);
		Mockito.when(paymentRepository.save(payment)).thenReturn(payment);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse = paymentServiceImpl.payment(user.getUserId(), paymentDto);

		assertEquals("purchase successfull", apiResponse.getMessage());
		assertEquals(HttpStatus.OK, apiResponse.getStatus());
		verify(cartRepository).findById(paymentDto.getCartId());
		verify(walletRepository).findById(paymentDto.getWalletId());
		verify(productRepository).findAllById(anyList());

	}

	@Test
	void testUserNotFound() {
		long userId = 1L;
		PaymentDto paymentDto = new PaymentDto(1L, 1L);

		Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

		assertThrows(UserNotFoundException.class, () -> paymentServiceImpl.payment(userId, paymentDto));
		verify(userRepository).findById(userId);
		verifyNoInteractions(cartRepository, walletRepository, productRepository);
	}

	@Test
	void testCartNotFound() {
		long userId = 1L;
		PaymentDto paymentDto = new PaymentDto(1L, 1L);
		User user = new User(userId, "Manoj", "manu@gmail.com");

		Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
		Mockito.when(cartRepository.findById(paymentDto.getCartId())).thenReturn(Optional.empty());

		assertThrows(CartNotFoundException.class, () -> paymentServiceImpl.payment(userId, paymentDto));
		verify(userRepository).findById(userId);
		verify(cartRepository).findById(paymentDto.getCartId());
		verifyNoInteractions(walletRepository, productRepository);
	}

	@Test
	void testPaymentWalletNotFound() {
		long userId = 1L;
		PaymentDto paymentDto = new PaymentDto(1L, 1L);
		User user = new User(userId, "Manoj", "manu@gmail.com");
		Cart cart = new Cart(1L, 8975, 5, user, new ArrayList<CartProduct>());

		Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
		Mockito.when(cartRepository.findById(cart.getCartId())).thenReturn(Optional.of(cart));
		Mockito.when(walletRepository.findById(1L)).thenReturn(Optional.empty());

		verifyNoInteractions(paymentRepository);
		assertThrows(WalletNotFoundException.class, () -> paymentServiceImpl.payment(userId, paymentDto));
		verify(userRepository).findById(userId);
		verify(cartRepository).findById(cart.getCartId());
		verify(walletRepository).findById(1L);
		verifyNoInteractions(paymentRepository);

	}

	@Test
	void testRequestedQuantityUnavailable() {
		Product product = new Product(1L, "Bat", 45, 665.45);
		Product product2 = new Product(2L, "Basket ball", 50, 1000);

		User user = new User(12L, "Manoj", "manu@gmail.com");

		Wallet wallet = new Wallet(1L, user, LocalDate.now().plusDays(5), 30000, "Paytm");

		CartProduct cartProduct = new CartProduct(123L, 1L, 3);
		CartProduct cartProduct2 = new CartProduct(234L, 2L, 55);

		List<CartProduct> cartProducts = new ArrayList<>();
		cartProducts.add(cartProduct);
		cartProducts.add(cartProduct2);

		Cart cart = new Cart(1L, 6996.35, 8, user, cartProducts);
		PaymentDto paymentDto = new PaymentDto(1L, 1L);

		Map<Long, Product> productMap = new HashMap<Long, Product>() {
			{
				put(product.getProductId(), product);
				put(product2.getProductId(), product2);
			}
		};
		List<Product> productList = new ArrayList<>(productMap.values());
		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
		Mockito.when(cartRepository.findById(paymentDto.getCartId())).thenReturn(Optional.of(cart));
		Mockito.when(walletRepository.findById(paymentDto.getWalletId())).thenReturn(Optional.of(wallet));
		Mockito.when(cartRepository.findByCartIdAndUserUserId(paymentDto.getCartId(), user.getUserId())).thenReturn(cart);
		Mockito.when(walletRepository.findByWalletIdAndUserUserId(paymentDto.getWalletId(), user.getUserId())).thenReturn(wallet);
		Mockito.when(productRepository.findAllById(anyList())).thenReturn(productList);
		Mockito.when(productRepository.saveAll(productMap.values())).thenReturn(productList);
		assertThrows(RequestedQuantityNotAvailableException.class, () -> paymentServiceImpl.payment(12L, paymentDto));

	}

	@Test
	void testInsufficientFund() {
		Product product = new Product(1L, "Bat", 45, 665.45);
		Product product2 = new Product(2L, "Basket ball", 50, 1000);

		User user = new User(12L, "Manoj", "manu@gmail.com");

		Wallet wallet = new Wallet(1L, user, LocalDate.now().plusDays(5), 6000, "Paytm");

		CartProduct cartProduct = new CartProduct(123L, 1L, 3);
		CartProduct cartProduct2 = new CartProduct(234L, 2L, 5);

		List<CartProduct> cartProducts = new ArrayList<>();
		cartProducts.add(cartProduct);
		cartProducts.add(cartProduct2);

		Cart cart = new Cart(1L, 6996.35, 8, user, cartProducts);
		PaymentDto paymentDto = new PaymentDto(1L, 1L);

		Map<Long, Product> productMap = new HashMap<Long, Product>() {
			{
				put(product.getProductId(), product);
				put(product2.getProductId(), product2);
			}
		};
		List<Product> productList = new ArrayList<>(productMap.values());

		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
		Mockito.when(cartRepository.findById(paymentDto.getCartId())).thenReturn(Optional.of(cart));
		Mockito.when(walletRepository.findById(paymentDto.getWalletId())).thenReturn(Optional.of(wallet));
		Mockito.when(cartRepository.findByCartIdAndUserUserId(paymentDto.getCartId(), user.getUserId())).thenReturn(cart);
		Mockito.when(walletRepository.findByWalletIdAndUserUserId(paymentDto.getWalletId(), user.getUserId())).thenReturn(wallet);
		Mockito.when(productRepository.findAllById(anyList())).thenReturn(productList);
		Mockito.when(productRepository.saveAll(productMap.values())).thenReturn(productList);
		assertThrows(InsufficientFundsException.class, () -> paymentServiceImpl.payment(12L, paymentDto));
	}

	@Test
	void testWalletExpired() {
		Product product = new Product(1L, "Bat", 45, 665.45);
		Product product2 = new Product(2L, "Basket ball", 50, 1000);

		User user = new User(12L, "Manoj", "manu@gmail.com");

		Wallet wallet = new Wallet(1L, user, LocalDate.now().minusDays(5), 7000, "Paytm");

		CartProduct cartProduct = new CartProduct(123L, 1L, 3);
		CartProduct cartProduct2 = new CartProduct(234L, 2L, 5);

		List<CartProduct> cartProducts = new ArrayList<>();
		cartProducts.add(cartProduct);
		cartProducts.add(cartProduct2);

		Cart cart = new Cart(1L, 6996.35, 8, user, cartProducts);
		PaymentDto paymentDto = new PaymentDto(1L, 1L);

		Map<Long, Product> productMap = new HashMap<Long, Product>() {
			{
				put(product.getProductId(), product);
				put(product2.getProductId(), product2);
			}
		};
		List<Product> productList = new ArrayList<>(productMap.values());

		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
		Mockito.when(cartRepository.findById(paymentDto.getCartId())).thenReturn(Optional.of(cart));
		Mockito.when(walletRepository.findById(paymentDto.getWalletId())).thenReturn(Optional.of(wallet));
		Mockito.when(cartRepository.findByCartIdAndUserUserId(paymentDto.getCartId(), user.getUserId())).thenReturn(cart);
		Mockito.when(walletRepository.findByWalletIdAndUserUserId(paymentDto.getWalletId(), user.getUserId())).thenReturn(wallet);
		Mockito.when(productRepository.findAllById(anyList())).thenReturn(productList);
		Mockito.when(productRepository.saveAll(productMap.values())).thenReturn(productList);
		assertThrows(WalletExpiredException.class, () -> paymentServiceImpl.payment(12L, paymentDto));

	}
	
	@Test
	void testInvalidUsersCart(){
		Product product = new Product(1L, "Bat", 45, 665.45);
		Product product2 = new Product(2L, "Basket ball", 50, 1000);

		User user = new User(12L, "Manoj", "manu@gmail.com");
		User user1=new User(1L,"Darshan","dar@gmail.com");
		Wallet wallet = new Wallet(1L, user1, LocalDate.now().plusDays(5), 30000, "Paytm");

		CartProduct cartProduct = new CartProduct(123L, 1L, 3);
		CartProduct cartProduct2 = new CartProduct(234L, 2L, 5);

		List<CartProduct> cartProducts = new ArrayList<>();
		cartProducts.add(cartProduct);
		cartProducts.add(cartProduct2);

		Cart cart = new Cart(1L, 6996.35, 8, user1, cartProducts);
		PaymentDto paymentDto = new PaymentDto(1L, 1L);
		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
		Mockito.when(cartRepository.findById(cart.getCartId())).thenReturn(Optional.of(cart));
		Mockito.when(walletRepository.findById(1L)).thenReturn(Optional.of(wallet));
		assertThrows(CartNotFoundException.class, () -> paymentServiceImpl.payment(12L, paymentDto));
		
		
	}
	
	@Test
	void testInvalidUsersWallet() {
		Product product = new Product(1L, "Bat", 45, 665.45);
		Product product2 = new Product(2L, "Basket ball", 50, 1000);

		User user = new User(12L, "Manoj", "manu@gmail.com");
		User user1=new User(1L,"Darshan","dar@gmail.com");
		Wallet wallet = new Wallet(1L, user1, LocalDate.now().plusDays(5), 30000, "Paytm");

		CartProduct cartProduct = new CartProduct(123L, 1L, 3);
		CartProduct cartProduct2 = new CartProduct(234L, 2L, 5);

		List<CartProduct> cartProducts = new ArrayList<>();
		cartProducts.add(cartProduct);
		cartProducts.add(cartProduct2);

		Cart cart = new Cart(1L, 6996.35, 8, user1, cartProducts);
		PaymentDto paymentDto = new PaymentDto(1L, 1L);
		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
		Mockito.when(cartRepository.findById(cart.getCartId())).thenReturn(Optional.of(cart));
		Mockito.when(walletRepository.findById(1L)).thenReturn(Optional.of(wallet));
		Mockito.when(cartRepository.findByCartIdAndUserUserId(cart.getCartId(), user.getUserId())).thenReturn(cart);
		
		assertThrows(WalletNotFoundException.class, () -> paymentServiceImpl.payment(12L, paymentDto));
		
		
	}
}
