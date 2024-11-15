package com.eshopping.profile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalProfileExceptionHandler {

	@ExceptionHandler(UserProfileNotFoundException.class)
	public ResponseEntity<String> handleUserProfileNotFoundException(UserProfileNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

}
