package com.ty.foodappapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.foodappapi.dto.Product;
import com.ty.foodappapi.repository.ProductRepository;
@Repository
public class ProductDao {
	@Autowired
	ProductRepository repository;
	
	public Product saveProduct(Product product) {
		return repository.save(product);
	}

	public Optional<Product> getProduct(int id) {
		

		return repository.findById(id);
	}

	public List<Product> getAllProduct() {
		return repository.findAll();
	}

	public void deleteProduct(Product product) {

		repository.delete(product);

	}


}
