package com.ty.foodappapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.foodappapi.dto.FoodOrder;
import com.ty.foodappapi.service.FoodOrderServices;
import com.ty.foodappapi.util.ResponseStructure;

@RestController
public class FoodOrderController {
	@Autowired
	FoodOrderServices services;
	@PostMapping("/foodorders")
	public ResponseEntity<ResponseStructure<FoodOrder>> saveProduct(@RequestBody FoodOrder foodOrder){
		return services.saveFoodOrder(foodOrder);
	}
	@PostMapping("/foodorders/{id}")
	public ResponseEntity<ResponseStructure<FoodOrder>> getFoodOrder(@PathVariable int id){
		return services.getFoodOrder(id);
	}

}
