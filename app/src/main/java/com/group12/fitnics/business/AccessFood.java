package com.group12.fitnics.business;

import com.group12.fitnics.application.Services;
import com.group12.fitnics.objects.Food;
import com.group12.fitnics.persistence.IFoodPersistence;

public class AccessFood{

    IFoodPersistence foodList;
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
    
    public IFoodPersistence getFoodList() {
        return foodList;
    }
    
    public void addFood(Food newFood){
        foodList.insertFood(newFood);
    }

    public boolean deleteFood(Food targetFood){
        boolean result = false;
        if(foodList.deleteFood(targetFood.getFoodID()).equals("success"))
            result = true;
        return result;
    }

    public boolean updateFood(Food newFood){
        boolean result = false;
        if(foodList.updateFood(newFood.getFoodID(), newFood).equals("success")){
            result = true;
        }
        return result;
    }
}
