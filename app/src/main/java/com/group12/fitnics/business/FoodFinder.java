package com.group12.fitnics.business;

import com.group12.fitnics.objects.Food;
import com.group12.fitnics.persistence.FoodPersistenceStub;

public class FoodFinder{

    FoodPersistenceStub foodList = new FoodPersistenceStub();
    public FoodFinder(FoodPersistenceStub list){
        foodList = list;
    }

    public Food search(String target){
        Food result = null;
        result = foodList.getFoodByFoodName(target);
        return result;
    }
}