package com.ty.foodappapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.foodappapi.dao.FoodOrderDao;
import com.ty.foodappapi.dto.FoodOrder;
import com.ty.foodappapi.dto.Item;
import com.ty.foodappapi.dto.Product;
import com.ty.foodappapi.exception.IdNotFoundException;
import com.ty.foodappapi.util.ResponseStructure;

@Service
public class FoodOrderServices {
	@Autowired
	FoodOrderDao dao;
	
	public ResponseEntity<ResponseStructure<FoodOrder>> saveFoodOrder(FoodOrder foodOrder) {
		double price=0;
		List<Item> items=foodOrder.getItems(); 		
		for (Item item : items) {
			price=price+item.getCost()*item.getQuantity();
			item.setFoodOrder(foodOrder);
		}
		foodOrder.setCost(price);
		ResponseStructure<FoodOrder> responseStructure = new ResponseStructure<FoodOrder>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("success");
		responseStructure.setData(dao.saveFoodOrder(foodOrder));
		return new ResponseEntity(responseStructure, HttpStatus.CREATED);

	}
	public ResponseEntity<ResponseStructure<FoodOrder>> getFoodOrder(int id){
		Optional<FoodOrder> optional = dao.getFoodOrder(id);
		ResponseStructure<FoodOrder> responseStructure = new ResponseStructure<FoodOrder>();
		if (optional.isPresent()) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(optional.get());
			return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure,HttpStatus.OK);
		}else {
			throw new IdNotFoundException("id "+id+", is exist");
		}
	}

}
