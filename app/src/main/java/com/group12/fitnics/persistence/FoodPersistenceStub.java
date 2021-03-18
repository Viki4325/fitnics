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
    public int insertFood(Food food) throws InvalidFoodException {
        if (food == null)
            throw new InvalidFoodException("The food is not valid. ");

        // if there exists same food name already, do not allow it to be inserted
        if (getFoodByFoodName(food.getName()) != null)
            throw new InvalidFoodException("There exists duplicate food. ");

        if(food.getName().length() > 20)
            throw new InvalidFdNameException("The name should be no more than 20 characters.");

        food.setFoodID();
        foods.add(food);

        return food.getFoodID();
    }

    // update food with foodID
    @Override
    public void updateFood(int foodID, Food food) throws InvalidFoodException, FoodNotFoundException {
        if (food == null)
            throw new InvalidFoodException("The food is not valid. ");

        boolean found = false;
        for(int i = 0; i < foods.size() && !found; i++) {
            if(foods.get(i).getFoodID() == foodID) {
                Food prevUser = foods.get(i);
                food.setFoodID(prevUser.getFoodID());
                foods.set(i, food);
                found = true;
            }
        }
        if (!found)
            throw new FoodNotFoundException("There's no food with the foodID. ");
    }

    // delete food by foodID
    @Override
    public void deleteFood(int foodID) throws FoodNotFoundException {
        boolean found = false;
        for(int i = 0; i < foods.size() && !found; i++) {
            if(foods.get(i).getFoodID() == foodID) {
                foods.remove(i);
                found = true;
            }
        }
        if (!found)
            throw new FoodNotFoundException("There's no food with the foodID. ");
    }
}
