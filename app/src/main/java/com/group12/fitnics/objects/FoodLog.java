package com.group12.fitnics.objects;

import com.group12.fitnics.business.AccessFood;
import com.group12.fitnics.business.DateHelper;
import java.time.LocalDate;

public class FoodLog {
    private int userID;
    private int foodID;
    private LocalDate date;
    private int grams;

    public FoodLog(int userId, int foodId, LocalDate date, int grams) {
        this.userID = userId;
        this.foodID = foodId;
        this.date = date;
        this.grams = grams;
    }

    public FoodLog(int userId, int foodId, String dateStr, int grams) {
        this.userID = userId;
        this.foodID = foodId;
        this.date = DateHelper.stringToDate(dateStr);
        this.grams = grams;
    }

    public int getUserID() {
        return userID;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDateString() {
        return DateHelper.dateToString(date);
    }

    public int getFoodID() {
        return foodID;
    }

    public int getGrams() {
        return grams;
    }

    public void setUserID(int id) { this.userID = id; }

    public void setFoodID(int id) { this.foodID = id; }

    public void setDate(LocalDate date) { this.date = date; }

    public void setGrams(int grams) { this.grams = grams; }

    public void setDateString(String dateStr) {
        this.date = DateHelper.stringToDate(dateStr);
    }

    public String toString(){
        AccessFood search = new AccessFood();
        Food food = search.searchByFoodID(foodID);

        return foodID +" "+ food.getName() + " " + date  + ", " + grams;
    }
}
