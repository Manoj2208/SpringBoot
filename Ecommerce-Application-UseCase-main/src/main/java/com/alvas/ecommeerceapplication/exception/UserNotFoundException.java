package com.alvas.ecommeerceapplication.exception;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
	}
	public UserNotFoundException() {
		super("User not found");
	}

}
