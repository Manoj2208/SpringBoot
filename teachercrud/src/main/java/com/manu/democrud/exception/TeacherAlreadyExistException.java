package com.manu.democrud.exception;

public class TeacherAlreadyExistException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public TeacherAlreadyExistException(String message) {
		super(message);
		this.message = message;
	}

}
