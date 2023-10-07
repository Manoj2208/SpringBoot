package com.alvas.ecommeerceapplication.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.alvas.ecommeerceapplication.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiResponse> handleUserNotFoundException(UserNotFoundException exception) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ApiResponse(exception.getMessage(), HttpStatus.NOT_FOUND));

	}

	@ExceptionHandler(CartNotFoundException.class)
	public ResponseEntity<ApiResponse> handleCartNotFoundException(CartNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ApiResponse(exception.getMessage(), HttpStatus.NOT_FOUND));

	}

	@ExceptionHandler(InsufficientFundsException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(InsufficientFundsException exception) {
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
				.body(new ApiResponse(exception.getMessage(), HttpStatus.PAYMENT_REQUIRED));

	}

	@ExceptionHandler(WalletExpiredException.class)
	public ResponseEntity<ApiResponse> handleResourceWalletExpiredException(WalletExpiredException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ApiResponse(exception.getMessage(), HttpStatus.GONE));

	}

	@ExceptionHandler(WalletNotFoundException.class)
	public ResponseEntity<ApiResponse> handleWalletNotFoundException(WalletNotFoundException exception) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ApiResponse(exception.getMessage(), HttpStatus.NOT_FOUND));

	}

	@ExceptionHandler(RequestedQuantityNotAvailableException.class)
	public ResponseEntity<ApiResponse> handleRequestedQuantityNotAvailableException(
			RequestedQuantityNotAvailableException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ApiResponse(exception.getMessage(), HttpStatus.BAD_REQUEST));

	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> productNotFoundException(ProductNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());

	}

	@ExceptionHandler(UserIdNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(UserIdNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ApiResponse(exception.getMessage(), HttpStatus.NOT_FOUND));

	}

	@ExceptionHandler(NoPurchaseHistoryFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(NoPurchaseHistoryFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ApiResponse(exception.getMessage(), HttpStatus.NOT_FOUND));

	}

}
