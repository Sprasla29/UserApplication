package com.example.UserApplication.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> customValidationErrorHandling(MethodArgumentNotValidException e){
	
		return new ResponseEntity<>(e.getFieldError(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
