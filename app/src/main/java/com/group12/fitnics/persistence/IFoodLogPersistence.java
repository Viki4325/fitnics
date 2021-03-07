package com.group12.fitnics.persistence;

import com.group12.fitnics.objects.FoodLog;
import com.group12.fitnics.objects.MyDate;

import java.util.List;

public interface IFoodLogPersistence {

    FoodLog getFoodLog(final int userID, final int exerciseID, final MyDate date);

    List<FoodLog> getFoodLogByUser(final int userID);

    List<FoodLog> getFoodLogByUserDate(final int userID, final MyDate date);

    void insertFoodLog(final FoodLog foodLog);

    void updateFoodLog(final int userID, final int foodID, final MyDate date, final FoodLog updatedLog);

    void deleteFoodLog(final int userID, final int foodID, final MyDate date);
}
