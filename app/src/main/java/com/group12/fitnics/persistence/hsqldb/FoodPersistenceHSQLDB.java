package com.group12.fitnics.persistence.hsqldb;

import com.group12.fitnics.objects.Food;
import com.group12.fitnics.persistence.IFoodPersistence;

import java.util.ArrayList;

public class FoodPersistenceHSQLDB implements IFoodPersistence {
    @Override
    public ArrayList<Food> getFoodSequential() {
        return null;
    }

    @Override
    public Food getFoodByID(int foodID) {
        return null;
    }

    @Override
    public Food getFoodByFoodName(String foodName) {
        return null;
    }

    @Override
    public void insertFood(Food currentUser) {

    }

    @Override
    public void updateFood(int foodID, Food currentFood) {

    }

    @Override
    public void deleteFood(int foodID) {

    }
}
