package com.alvas.ecommeerceapplication.exception;

public class WalletExpiredException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public WalletExpiredException(String message) {
		super(message);
	}
	public WalletExpiredException() {
		super("Wallet Expired");
	}

}
