package com.group12.fitnics.persistence;

import com.group12.fitnics.exceptions.FoodNotFoundException;
import com.group12.fitnics.exceptions.InvalidFdNameException;
import com.group12.fitnics.exceptions.InvalidFoodException;
import com.group12.fitnics.objects.Food;

import java.util.ArrayList;

public class FoodPersistenceStub implements IFoodPersistence {
    private ArrayList<Food> foods;

    public FoodPersistenceStub() {
        this.foods = new ArrayList<>();
        Food bread = new Food(1, "Bread",  2.33);
        Food yogurt = new Food(2, "Nonfat Greek yogurt", 0.53);
        Food egg = new Food(3, "Fried Egg", 1.84);
        Food banana = new Food(4, "Banana", 0.89);
        Food rice = new Food(5, "Steamed rice", 1.93);
        Food chicken = new Food(6, "Grilled chicken breast", 1.65);
        foods.add(bread);
        foods.add(yogurt);
        foods.add(egg);
        foods.add(banana);
        foods.add(rice);
        foods.add(chicken);
        Food.setLastFoodID(6);
    }

    public ArrayList<Food> getFoodSequential() {
        return foods;
    }

    // get food by foodID
    @Override
    public Food getFoodByID(int foodID) {
        Food result = null;
        for(int i = 0; i < foods.size() && result == null; i++) {
            if(foods.get(i).getFoodID() == foodID)
                result = foods.get(i);
        }
        return result;
    }

    @Override
    public Food getFoodByFoodName(String foodName) {
        Food result = null;
        for(int i = 0; i < foods.size() && result == null; i++) {
            if(foods.get(i).getName().equalsIgnoreCase(foodName))
                result = foods.get(i);
        }
        return result;
    }

    @Override
    public int insertFood(Food food) {
        food.setFoodID();
        foods.add(food);

        return food.getFoodID();
    }

    // update food with foodID
    @Override
    public void updateFood(int foodID, Food food) {
        boolean found = false;
        for(int i = 0; i < foods.size() && !found; i++) {
            if(foods.get(i).getFoodID() == foodID) {
                Food prevUser = foods.get(i);
                food.setFoodID(prevUser.getFoodID());
                foods.set(i, food);
                found = true;
            }
        }
    }

    // delete food by foodID
    @Override
    public void deleteFood(int foodID) {
        boolean found = false;
        for(int i = 0; i < foods.size() && !found; i++) {
            if(foods.get(i).getFoodID() == foodID) {
                foods.remove(i);
                found = true;
            }
        }
    }
}
