package com.ty.foodappapi.exception;

public class InvalidCredentialsException extends RuntimeException{
	String message="invalid credentials";

	public InvalidCredentialsException() {
		
	}

	public InvalidCredentialsException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		
		return message;
	}
	

}
