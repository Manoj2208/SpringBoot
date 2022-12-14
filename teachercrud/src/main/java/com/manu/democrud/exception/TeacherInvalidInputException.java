package com.manu.democrud.exception;

public class TeacherInvalidInputException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public TeacherInvalidInputException(String message) {
		super(message);
		this.message = message;
	}

}
