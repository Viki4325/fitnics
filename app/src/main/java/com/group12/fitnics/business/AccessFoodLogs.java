package com.group12.fitnics.business;

import com.group12.fitnics.application.Services;
import com.group12.fitnics.objects.FoodLog;
import com.group12.fitnics.objects.MyDate;
import com.group12.fitnics.persistence.IFoodLogPersistence;
import com.group12.fitnics.persistence.IFoodPersistence;

import java.util.List;

public class AccessFoodLogs {

    private IFoodLogPersistence foodLogPersistence;
    private IFoodPersistence foodPersistence;

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
        int result = 0;
        if (logs.size() > 0)
            result = getUserTotalDailyIntake(logs, foodPersistence);
        return result;
    }

    private static int getUserTotalDailyIntake(List<FoodLog> logs, IFoodPersistence foods) {
        int total = 0;
        for (int i = 0; i < logs.size(); i++) {
            int gram = logs.get(i).getGrams();
            double caloriesPerGram = foods.getFoodByID(logs.get(i).getFoodID()).getCalories();
            total += gram * caloriesPerGram;
        }
        return total;
    }


}
