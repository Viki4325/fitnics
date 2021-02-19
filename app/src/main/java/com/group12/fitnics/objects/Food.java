package com.group12.fitnics.objects;

public class Food {
    private int foodID;
    private String name;
    private int caloriesPerGram;

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int id) {
        this.foodID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return caloriesPerGram;
    }

    public void setCalories(int calories) {
        this.caloriesPerGram = calories;
    }
}
