package com.manu.democrud.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ErrorResponse errorResponse = new ErrorResponse(4008l, details);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(TeacherInvalidInputException.class)
	public ResponseEntity<Object> teacherInputInvalid(TeacherInvalidInputException ex, WebRequest req) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse errorResponse = new ErrorResponse(5008l, details);
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(TeacherNotFoundException.class)
	public @ResponseBody ErrorResponseAlternative handleTeacherNotFoundException(TeacherNotFoundException ex) {
		return new ErrorResponseAlternative(HttpStatus.NOT_FOUND.value(), ex.getLocalizedMessage());
	}

	@ExceptionHandler(TeacherAlreadyExistException.class)
	public @ResponseBody ErrorResponseAlternative handleTeacherAlreadyExistException(TeacherAlreadyExistException ex) {
		return new ErrorResponseAlternative(HttpStatus.ALREADY_REPORTED.value(), ex.getLocalizedMessage());
	}

}
