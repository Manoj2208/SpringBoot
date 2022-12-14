package com.manu.democrud.exception;

public class TeacherNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public TeacherNotFoundException(String message) {
		super(message);
		this.message = message;
	}
	
	

}
