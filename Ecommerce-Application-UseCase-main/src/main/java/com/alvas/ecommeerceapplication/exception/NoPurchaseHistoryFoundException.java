package com.alvas.ecommeerceapplication.exception;

public class NoPurchaseHistoryFoundException extends RuntimeException {
	
	
	
	

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NoPurchaseHistoryFoundException(String message) {
			super(message);
		}

		public NoPurchaseHistoryFoundException() {
			super("No Order History Is Found");
		}

}

