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
        if (!checkInvariant(foodLog))
            throw new InvalidFoodLogException("The food log has invalid userID or foodID or grams. ");

        // if there exists same food log already, to not allow it to be inserted
        if (getFoodLog(foodLog.getUserID(), foodLog.getFoodID(), foodLog.getDate()) != null)
            throw new InvalidFoodLogException("The food log is duplicate. \n You can instead go to FoodLog and add extra amount of grams consumed. Thank you!");

        foodLogPersistence.insertFoodLog(foodLog);
    }

    public void updateFoodLog(int userID, int foodID, LocalDate date, FoodLog updatedLog) throws InvalidFoodLogException, FoodLogNotFoundException {
        if (!checkInvariant(updatedLog))
            throw new InvalidFoodLogException("The food log has invalid userID or foodID or grams. ");

        if (getFoodLog(userID, foodID, date) == null)
            throw new FoodLogNotFoundException("There is no such food log to update. ");

        foodLogPersistence.updateFoodLog(userID, foodID, date, updatedLog);
    }

    public void deleteFoodLog(int userID, int foodID, LocalDate date) throws FoodLogNotFoundException {
        if (getFoodLog(userID, foodID, date) == null)
            throw new FoodLogNotFoundException("There is no such food log to update. ");

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

    private boolean checkInvariant(FoodLog foodLog) {
        return foodLog != null && foodLog.getUserID() >= 0 && foodLog.getFoodID() >= 0 && foodLog.getGrams() > 0;
    }
}
