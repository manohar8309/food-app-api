package com.ty.foodappapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.foodappapi.dao.ProductDao;
import com.ty.foodappapi.dto.Product;
import com.ty.foodappapi.exception.IdNotFoundException;
import com.ty.foodappapi.util.ResponseStructure;

@Service
public class ProductService {
	@Autowired
	ProductDao dao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product) {
		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("success");
		responseStructure.setData(dao.saveProduct(product));
		return new ResponseEntity(responseStructure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(int id, Product product) {
		Optional<Product> optional = dao.getProduct(id);
		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		if (optional.isPresent()) {
			Product product1 = optional.get();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			product1.setOffer(product.getOffer());
			product1.setName(product.getName());
			product1.setPrice(product.getPrice());
			product1.setType(product.getType());

			responseStructure.setData(dao.saveProduct(product1));
			return new ResponseEntity(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<String>> deleteProduct(int id) {
		Optional<Product> optional = dao.getProduct(id);
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		if (optional.isPresent()) {
			dao.deleteProduct(optional.get());
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData("deleted");
			return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);

		} else {
			throw new IdNotFoundException("id " + id + ", is not found");
		}
	}
	
	public ResponseEntity<ResponseStructure<Product>> getProduct(int id){
		Optional<Product> optional = dao.getProduct(id);
		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		if (optional.isPresent()) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(optional.get());
			return new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.OK);
		}else {
			throw new IdNotFoundException("id "+id+", is exist");
		}
	}
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProduct(){
		List<Product> list = dao.getAllProduct();
		ResponseStructure<List<Product>> responseStructure = new ResponseStructure<List<Product>>();
		
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(list);
			return new ResponseEntity<ResponseStructure<List<Product>>>(responseStructure,HttpStatus.OK);
		
	}

}
