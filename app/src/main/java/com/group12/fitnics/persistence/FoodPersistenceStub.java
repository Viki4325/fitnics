package com.group12.fitnics.persistence;

import com.group12.fitnics.objects.Food;

import java.util.ArrayList;

public class FoodPersistenceStub implements FoodPersistence {
    private ArrayList<Food> foods;

    public FoodPersistenceStub() {
        this.foods = new ArrayList<>();
        Food apple = new Food(1, "Apple",  100);
        Food pizza = new Food(2, "Pizza", 175);
        Food spaghetti = new Food(3, "Spaghetti", 160);
        Food hamburg = new Food(4, "Hamburg", 160);
        foods.add(apple);
        foods.add(pizza);
        foods.add(spaghetti);
        foods.add(hamburg);
    }

    public ArrayList<Food> getFoodSequential() {
        return foods;
    }

    // get a user by userID
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
    public String insertFood(Food food) {
        food.setFoodID(0);
        foods.add(food);
        return "success";
    }

    // update a user with userID to the currentUser
    @Override
    public String updateFood(int foodID, Food food) {
        boolean found = false;
        for(int i = 0; i < foods.size() && !found; i++) {
            if(foods.get(i).getFoodID() == foodID) {
                Food prevUser = foods.get(i);
                food.setFoodID(prevUser.getFoodID());
                foods.set(i, food);
                found = true;
            }
        }
        return "success";
    }

    // delete a user by userID
    @Override
    public String deleteFood(int foodID) {
        boolean found = false;
        for(int i = 0; i < foods.size() && !found; i++) {
            if(foods.get(i).getFoodID() == foodID) {
                foods.remove(i);
                found = true;
            }
        }
        return "success";
    }
}

