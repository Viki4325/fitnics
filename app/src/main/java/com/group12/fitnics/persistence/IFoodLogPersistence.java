package com.group12.fitnics.persistence;

import com.group12.fitnics.objects.FoodLog;

import java.time.LocalDate;
import java.util.List;

public interface IFoodLogPersistence {

    FoodLog getFoodLog(final int userID, final int foodID, final LocalDate date);

    List<FoodLog> getFoodLogByUser(final int userID);

    List<FoodLog> getFoodLogByUserDate(final int userID, final LocalDate date);

    void insertFoodLog(final FoodLog foodLog);

    void updateFoodLog(final int userID, final int foodID, final LocalDate date, final FoodLog updatedLog);

    void deleteFoodLog(final int userID, final int foodID, final LocalDate date);

}
