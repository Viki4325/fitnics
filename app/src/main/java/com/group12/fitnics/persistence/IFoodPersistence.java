package com.group12.fitnics.persistence;

import com.group12.fitnics.objects.Food;

import java.util.ArrayList;
import java.util.List;

public interface IFoodPersistence {
    List<Food> getFoodSequential();

    Food getFoodByID(final int foodID);

    Food getFoodByFoodName(final String foodName);

    int insertFood(final Food currentFood);

    void updateFood(final int foodID, final Food currentFood);

    void deleteFood(final int foodID);
}
