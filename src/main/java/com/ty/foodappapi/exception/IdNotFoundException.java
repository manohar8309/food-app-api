package com.ty.foodappapi.exception;

public class IdNotFoundException extends RuntimeException {
	String msg = "given id is not found";

	public IdNotFoundException(String msg) {

		this.msg = msg;
	}

	public IdNotFoundException() {

	}

	@Override
	public String getMessage() {

		return msg;
	}

}
