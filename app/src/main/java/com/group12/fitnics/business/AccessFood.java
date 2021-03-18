package com.group12.fitnics.business;

import com.group12.fitnics.application.Services;
import com.group12.fitnics.exceptions.FoodNotFoundException;
import com.group12.fitnics.exceptions.InvalidFoodException;
import com.group12.fitnics.objects.Food;
import com.group12.fitnics.persistence.IFoodPersistence;

import java.util.List;

public class AccessFood{

    private IFoodPersistence foodList;

    public AccessFood(IFoodPersistence foodList) {
        this.foodList = foodList;
    }

    public AccessFood(){
        foodList = Services.getFoodPersistence();
    }

    public Food searchByFoodName(String target) {
        return foodList.getFoodByFoodName(target);
    }
    
    public Food searchByFoodID(int foodID) {
        return foodList.getFoodByID(foodID);
    }

    public List<Food> getFoodList() {
        return foodList.getFoodSequential();
    }
    
    public int addFood(Food newFood) throws InvalidFoodException {
        return foodList.insertFood(newFood);
    }

    public void deleteFood(int foodID) throws InvalidFoodException, FoodNotFoundException {
        foodList.deleteFood(foodID);
    }

    public void updateFood(int foodID, Food newFood) throws FoodNotFoundException {
        foodList.updateFood(foodID, newFood);
    }
}
