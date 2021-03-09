package com.group12.fitnics.business;

import com.group12.fitnics.application.Services;
import com.group12.fitnics.exceptions.FoodLogNotFoundException;
import com.group12.fitnics.exceptions.InvalidFoodLogException;
import com.group12.fitnics.objects.FoodLog;
import com.group12.fitnics.persistence.IFoodLogPersistence;
import com.group12.fitnics.persistence.IFoodPersistence;

import java.time.LocalDate;
import java.util.List;

public class AccessFoodLogs {

    private IFoodLogPersistence foodLogPersistence;
    private IFoodPersistence foodPersistence;

    public AccessFoodLogs(IFoodLogPersistence foodLogPersistence, IFoodPersistence foodPersistence) {
        this.foodLogPersistence = foodLogPersistence;
        this.foodPersistence = foodPersistence;
    }

    public AccessFoodLogs() {
        foodLogPersistence = Services.getFoodLogPersistence();
        foodPersistence = Services.getFoodPersistence();
    }

    public FoodLog getFoodLog(int userID, int exerciseID, LocalDate date) {
        return foodLogPersistence.getFoodLog(userID, exerciseID, date);
    }

    public List<FoodLog> getFoodLogByUser(int userID) {
        return foodLogPersistence.getFoodLogByUser(userID);
    }

    public List<FoodLog> getFoodLogByUserDate(int userID, LocalDate date) {
        return foodLogPersistence.getFoodLogByUserDate(userID, date);
    }

    public void insertFoodLog(FoodLog foodLog) throws InvalidFoodLogException {
        foodLogPersistence.insertFoodLog(foodLog);
    }

    public void updateFoodLog(int userID, int foodID, LocalDate date, FoodLog updatedLog) throws InvalidFoodLogException, FoodLogNotFoundException {
        foodLogPersistence.updateFoodLog(userID, foodID, date, updatedLog);
    }

    public void deleteFoodLog(int userID, int foodID, LocalDate date) throws FoodLogNotFoundException {
        foodLogPersistence.deleteFoodLog(userID, foodID, date);
    }

    public int getUserTotalDailyIntake(int userID, LocalDate date) {
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
