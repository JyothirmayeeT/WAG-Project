package com.example.wag.exception;

import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Data;


public class CustomApiException extends RuntimeException{

	private HttpStatusCode httpStatusCode;
	private String errorMessage;	
	
	public CustomApiException(HttpStatusCode statusCode, String statusText) {
		this.httpStatusCode = statusCode;
		this.errorMessage = statusText;
	}

	public HttpStatusCode getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(HttpStatusCode httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	

}
