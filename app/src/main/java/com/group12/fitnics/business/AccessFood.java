package com.group12.fitnics.business;

import com.group12.fitnics.application.Services;
import com.group12.fitnics.objects.Food;
import com.group12.fitnics.persistence.FoodPersistence;
import com.group12.fitnics.persistence.FoodPersistenceStub;

public class AccessFood{

    FoodPersistence foodList;
    public AccessFood(){
        foodList = Services.getFoodPersistence();
    }

    public Food searchByFoodName(String target){
        Food result = null;
        result = foodList.getFoodByFoodName(target);
        return result;
    }

    public Food searchByFoodID(int foodID){
        Food result = null;
        result = foodList.getFoodByID(foodID);
        return result;
    }

    public void addFood(Food newFood){
        foodList.insertFood(newFood);
    }

    public boolean deleteFood(Food targetFood){
        boolean result = false;
        if(foodList.deleteFood(targetFood.getFoodID()))
            result = true;
        return result;
    }

    public boolean updateFood(Food newFood){
        boolean result = false;
        if(foodList.updateFood(newFood.getFoodID(), newFood)){
            result = true;
        }
        return result;
    }
}