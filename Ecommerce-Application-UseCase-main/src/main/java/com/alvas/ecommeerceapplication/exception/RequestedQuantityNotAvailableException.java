package com.alvas.ecommeerceapplication.exception;

public class RequestedQuantityNotAvailableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RequestedQuantityNotAvailableException(String message) {
		super(message);
	}

	public RequestedQuantityNotAvailableException() {
		super("requested quantity unavailable");
	}
}
