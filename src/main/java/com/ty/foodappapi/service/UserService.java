package com.ty.foodappapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.foodappapi.dao.UserDao;
import com.ty.foodappapi.dto.Login;
import com.ty.foodappapi.dto.User;
import com.ty.foodappapi.exception.IdNotFoundException;
import com.ty.foodappapi.util.ResponseStructure;

@Service
public class UserService {
	@Autowired
	UserDao dao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("success");
		responseStructure.setData(dao.saveUser(user));
		return new ResponseEntity(responseStructure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<User>> validateUser(Login login) {
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		User user = dao.validateUser(login.getEmail(), login.getPassword());
		if (user != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(user);
			return new ResponseEntity(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("invalid credentials");
			return new ResponseEntity(responseStructure, HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<User>> updateUser(int id, User user) {
		Optional<User> optional = dao.getUser(id);
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		if (optional.isPresent()) {
			User user1=optional.get();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");

			user1.setName(user.getName());
			user1.setEmail(user.getEmail());
			user1.setGender(user.getGender());
			user1.setPassword(user.getPassword());
			user1.setPhone(user.getPhone());
			user1.setRole(user.getRole());
			responseStructure.setData(dao.saveUser(user1));
			return new ResponseEntity(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteUser(int id) {
		Optional<User> optional = dao.getUser(id);
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		if (optional.isPresent()) {
			dao.deleteUser(optional.get());
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData("deleted");
			return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK); 

		}else {
			throw new IdNotFoundException("id "+id+", is not found");
		}
	}
	public ResponseEntity<ResponseStructure<User>> getUser(int id){
		Optional<User> optional = dao.getUser(id);
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		if (optional.isPresent()) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(optional.get());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
		}else {
			throw new IdNotFoundException("id "+id+", is exist");
		}
	}
	public ResponseEntity<ResponseStructure<List<User>>> getAllUser(){
		List<User> list = dao.getAllUser();
		ResponseStructure<List<User>> responseStructure = new ResponseStructure<List<User>>();
		
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(list);
			return new ResponseEntity<ResponseStructure<List<User>>>(responseStructure,HttpStatus.OK);
		
	}

}
