package com.ty.foodappapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.foodappapi.dto.FoodOrder;
import com.ty.foodappapi.repository.FoodOrderRepository;

@Repository
public class FoodOrderDao {
	@Autowired
	FoodOrderRepository repository;
	
	public FoodOrder saveFoodOrder(FoodOrder foodOrder) {
		return repository.save(foodOrder);
	}
	
	public Optional<FoodOrder> getFoodOrder(int id) {
		return repository.findById(id);
	}
	
	public void deleteFoodOrder(FoodOrder foodOrder) {
		repository.delete(foodOrder);
	}
	
	public List<FoodOrder> getAllFoodOrder(){
		return repository.findAll();
		
	}

}
