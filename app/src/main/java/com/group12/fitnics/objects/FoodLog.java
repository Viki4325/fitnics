package com.group12.fitnics.objects;

public class FoodLog {
    private int userID;
    private int foodID;
    private String date;
    private int grams;

    public FoodLog(int userId, int foodId, String date, int grams) {
        this.userID = userId;
        this.foodID = foodId;
        this.date = date;
        this.grams = grams;
    }

    public int getUserID() {
        return userID;
    }
    public String getDate() {
        return date;
    }
    public int getFoodID() {
        return foodID;
    }
    public int getGrams() {
        return grams;
    }

    public void setUserID(int id) { this.userID = id; }
    public void setFoodID(int id) { this.foodID = id; }
    public void setDate(String date) { this.date = date; }
    public void setGrams(int grams) { this.grams = grams; }

}
