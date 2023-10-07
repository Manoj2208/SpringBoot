package com.alvas.ecommeerceapplication.exception;

public class CartNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CartNotFoundException(String message) {
		super(message);
	}
	public CartNotFoundException() {
		super("Cart not found");
	}

}
