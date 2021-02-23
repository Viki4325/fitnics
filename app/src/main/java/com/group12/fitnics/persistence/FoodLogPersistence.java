package com.group12.fitnics.persistence;

import com.group12.fitnics.objects.FoodLog;
import com.group12.fitnics.objects.MyDate;

import java.util.List;

public interface FoodLogPersistence {

    FoodLog getFoodLog(final int userID, final int exerciseID, final MyDate date);

    List<FoodLog> getFoodLogByUser(final int userID);

    List<FoodLog> getFoodLogByUserDate(final int userID, final MyDate date);

    String insertFoodLog(final FoodLog foodLog);

    String updateFoodLog(final int userID, final int foodID, final MyDate date, final FoodLog updatedLog);

    String deleteFoodLog(final int userID, final int foodID, final MyDate date);
}
