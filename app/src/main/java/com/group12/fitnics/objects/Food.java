package com.group12.fitnics.objects;

import java.io.Serializable;

public class Food implements Serializable {
    private int foodID;
    private String name;
    private double caloriesPerGram;
    private static int lastFoodID = -1;

    public Food(int foodID, String name, double caloriesPerGram) {
        this.foodID = foodID;
        this.name = name;
        this.caloriesPerGram = caloriesPerGram;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int id) {
        this.foodID = id;
    }

    public String getName() {
        return name;
    }

    // set the foodID only when inserting a food
    public void setFoodID() {
        lastFoodID++;
        this.foodID = lastFoodID;
    }

    public static void setLastFoodID(int i) {
        lastFoodID = i;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return caloriesPerGram;
    }

    public void setCalories(double calories) {
        this.caloriesPerGram = calories;
    }

    public String toString(){
        return foodID +"."+name +".\n"+ caloriesPerGram + " calories per gram";
    }
}
