package com.billing.service;

import com.billing.model.FoodItem;
import com.billing.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    public List<FoodItem> getAllItems() {
        return foodItemRepository.findAll();
    }

    public FoodItem getItemById(Long id) {
        return foodItemRepository.findById(id).orElse(null);
    }

    public FoodItem saveItem(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    public void deleteItem(Long id) {
        foodItemRepository.deleteById(id);
    }
}
