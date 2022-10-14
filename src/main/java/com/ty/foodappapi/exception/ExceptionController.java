package com.ty.foodappapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ty.foodappapi.util.ResponseStructure;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(IdNotFoundException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("id not found");
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ResponseStructure<String>> handleInvalidCredentialsException(InvalidCredentialsException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("invalid credentials ");
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}


}
