package com.group12.fitnics.persistence;

import com.group12.fitnics.objects.Food;

import java.util.ArrayList;

public interface IFoodPersistence {
    ArrayList<Food> getFoodSequential();

    Food getFoodByID(final int foodID);

    Food getFoodByFoodName(final String foodName);

    void insertFood(final Food currentFood);

    void updateFood(final int foodID, final Food currentFood);

    void deleteFood(final int foodID);
}
