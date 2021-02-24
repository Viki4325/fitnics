package com.group12.fitnics.business;

import com.group12.fitnics.application.Services;
import com.group12.fitnics.objects.FoodLog;
import com.group12.fitnics.objects.MyDate;
import com.group12.fitnics.persistence.FoodLogPersistence;
import com.group12.fitnics.persistence.FoodPersistence;

import java.util.List;

public class AccessFoodLogs {

    private FoodLogPersistence foodLogPersistence;
    private FoodPersistence foodPersistence;

    public AccessFoodLogs() {
        foodLogPersistence = Services.getFoodLogPersistence();
        foodPersistence = Services.getFoodPersistence();
    }

    public FoodLog getFoodLog(int userID, int exerciseID, MyDate date) {
        return foodLogPersistence.getFoodLog(userID, exerciseID, date);
    }

    public List<FoodLog> getFoodLogByUser(int userID) {
        return foodLogPersistence.getFoodLogByUser(userID);
    }

    public List<FoodLog> getFoodLogByUserDate(int userID, MyDate date) {
        return foodLogPersistence.getFoodLogByUserDate(userID, date);
    }

    public String insertFoodLog(FoodLog foodLog) {
        return foodLogPersistence.insertFoodLog(foodLog);
    }

    public String updateFoodLog(int userID, int foodID, MyDate date, FoodLog updatedLog) {
        return foodLogPersistence.updateFoodLog(userID, foodID, date, updatedLog);
    }

    public String deleteFoodLog(int userID, int foodID, MyDate date) {
        return foodLogPersistence.deleteFoodLog(userID, foodID, date);
    }

    public int getUserTotalDailyIntake(int userID, MyDate date) {
        List<FoodLog> logs = getFoodLogByUserDate(userID, date);
        return getUserTotalDailyIntake(logs, foodPersistence);
    }

    private static int getUserTotalDailyIntake(List<FoodLog> logs, FoodPersistence foods) {
        int total = 0;
        for (int i = 0; i < logs.size(); i++) {
            int gram = logs.get(i).getGrams();
            double caloriesPerGram = foods.getFoodByID(logs.get(i).getFoodID()).getCalories();
            total += gram * caloriesPerGram;
        }
        return total;
    }


}
