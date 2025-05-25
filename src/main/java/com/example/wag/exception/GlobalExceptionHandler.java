package com.example.wag.exception;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String,String>> handleConstraintViolationExceltion(ConstraintViolationException ex){
		Map<String,String> errors = ex.getConstraintViolations()
		.stream()
		.collect(Collectors.toMap(violation -> violation.getPropertyPath().toString(),
				violation -> violation.getMessage().toString()));
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomApiException.class)
    public ResponseEntity<String> handleCustomApiException(CustomApiException ex) {
        return ex.getHttpStatusCode().equals(HttpStatus.NOT_FOUND)?new ResponseEntity<>((ex.getErrorMessage()), HttpStatus.NOT_FOUND):
        	new ResponseEntity<>("UpStream API Failure",HttpStatus.BAD_GATEWAY);
    }

}
