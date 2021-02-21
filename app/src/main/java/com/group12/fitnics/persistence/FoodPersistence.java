package com.group12.fitnics.persistence;

import com.group12.fitnics.objects.Food;

import java.util.List;

public interface FoodPersistence {
    List<Food> getFoodSequential();

    Food getFoodByID(final int foodID);

    Food getFoodByFoodName(final String foodName);

    String insertFood(final Food currentUser);

    String updateFood(final int foodID, final Food currentFood);

    String deleteFood(final int foodID);
}
