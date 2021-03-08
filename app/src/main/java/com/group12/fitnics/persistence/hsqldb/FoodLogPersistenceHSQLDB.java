package com.group12.fitnics.persistence.hsqldb;

import com.group12.fitnics.objects.FoodLog;
import com.group12.fitnics.objects.MyDate;
import com.group12.fitnics.persistence.IFoodLogPersistence;

import java.util.List;

public class FoodLogPersistenceHSQLDB implements IFoodLogPersistence {
    @Override
    public FoodLog getFoodLog(int userID, int exerciseID, MyDate date) {
        return null;
    }

    @Override
    public List<FoodLog> getFoodLogByUser(int userID) {
        return null;
    }

    @Override
    public List<FoodLog> getFoodLogByUserDate(int userID, MyDate date) {
        return null;
    }

    @Override
    public void insertFoodLog(FoodLog foodLog) {

    }

    @Override
    public void updateFoodLog(int userID, int foodID, MyDate date, FoodLog updatedLog) {

    }

    @Override
    public void deleteFoodLog(int userID, int foodID, MyDate date) {

    }
}
