package com.manu.democrud.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseAlternative {
	int errorCode;
	String message;

	public ErrorResponseAlternative(int errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

}
