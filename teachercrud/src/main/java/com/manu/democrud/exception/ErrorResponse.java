package com.manu.democrud.exception;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
	Long errorCode;
	List<String> errors;
	public ErrorResponse(Long errorCode, List<String> errors) {
		super();
		this.errorCode = errorCode;
		this.errors = errors;
	}

}
