package com.alvas.ecommeerceapplication.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alvas.ecommeerceapplication.dto.PaymentDto;
import com.alvas.ecommeerceapplication.response.ApiResponse;
import com.alvas.ecommeerceapplication.response.PurchaseHistoryResponse;
import com.alvas.ecommeerceapplication.service.PaymentService;

@RestController
@RequestMapping("/users")
public class PaymentController {
	@Autowired
	PaymentService paymentService;

	@GetMapping("/{userId}")
	public ResponseEntity<List<PurchaseHistoryResponse>> getUserPurchasesForMonth(@PathVariable Long userId,
			@RequestParam("year") int year, @RequestParam("month") int month, @RequestParam int pageNumber,
			@RequestParam int pageSize) {
		List<PurchaseHistoryResponse> purchases = paymentService.getUserPurchasesForMonth(userId,
				LocalDate.of(year, month, 1), pageNumber, pageSize);

		return ResponseEntity.ok(purchases);
	}

	@PostMapping("/{user-id}/carts")
	public ResponseEntity<ApiResponse> payment(@Valid @RequestHeader long userID, @RequestBody PaymentDto paymentDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.payment(userID, paymentDto));

	}

}
