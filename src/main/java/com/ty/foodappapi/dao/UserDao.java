package com.ty.foodappapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.foodappapi.dto.User;
import com.ty.foodappapi.repository.UserRepository;

@Repository
public class UserDao {
	@Autowired
	UserRepository repository;

	public User saveUser(User user) {
		return repository.save(user);
	}

	public Optional<User> getUser(int id) {
		

		return repository.findById(id);
	}

	public List<User> getAllUser() {
		return repository.findAll();
	}

	public void deleteUser(User user) {

		repository.delete(user);

	}

	public User validateUser(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}

}
