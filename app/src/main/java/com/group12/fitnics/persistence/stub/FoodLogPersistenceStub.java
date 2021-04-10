package com.group12.fitnics.persistence.stub;

import com.group12.fitnics.exceptions.FoodLogNotFoundException;
import com.group12.fitnics.exceptions.InvalidFoodLogException;
import com.group12.fitnics.objects.FoodLog;
import com.group12.fitnics.persistence.IFoodLogPersistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FoodLogPersistenceStub implements IFoodLogPersistence {
    private List<FoodLog> foodLogs;

    public FoodLogPersistenceStub() {
        foodLogs = new ArrayList<>();

        LocalDate date1 = LocalDate.of(2021, 1, 1);
        LocalDate date2 = LocalDate.of(2021, 1, 2);
        LocalDate date3 = LocalDate.of(2021, 1, 17);

        foodLogs.add(new FoodLog(0, 1, date1, 25));
        foodLogs.add(new FoodLog(0, 2, date1, 200));
        foodLogs.add(new FoodLog(1, 3, date3, 50));
        foodLogs.add(new FoodLog(2, 1, date1, 30));
        foodLogs.add(new FoodLog(2, 2, date1, 150));
        foodLogs.add(new FoodLog(2, 5, date1, 200));
        foodLogs.add(new FoodLog(2, 6, date1, 100));
        foodLogs.add(new FoodLog(2, 4, date2, 150));
        foodLogs.add(new FoodLog(2, 5, date2, 200));
    }

    @Override
    public FoodLog getFoodLog(int userID, int foodID, LocalDate date) {
        boolean found = false;
        FoodLog result = null;
        for (int i = 0; i < foodLogs.size() && !found; i++) {
            FoodLog log = foodLogs.get(i);
            if (log.getUserID() == userID && log.getFoodID() == foodID && log.getDate().isEqual(date)) {
                found = true;
                result = log;
            }
        }
        return result;
    }

    @Override
    public List<FoodLog> getFoodLogByUser(int userID) {
        List<FoodLog> list = new ArrayList<>();
        for (int i = 0; i < foodLogs.size(); i++) {
            if (foodLogs.get(i).getUserID() == userID)
                list.add(foodLogs.get(i));
        }
        return list;
    }

    @Override
    public List<FoodLog> getFoodLogByUserDate(int userID, LocalDate date) {
        List<FoodLog> list = new ArrayList<>();
        for (int i = 0; i < foodLogs.size(); i++) {
            FoodLog log = foodLogs.get(i);
            if (log.getUserID() == userID && log.getDate().isEqual(date))
                list.add(log);
        }
        return list;
    }

    @Override
    public void insertFoodLog(FoodLog foodLog)  {

        foodLogs.add(foodLog);
    }

    @Override
    public void updateFoodLog(int userID, int foodID, LocalDate date, FoodLog updatedLog) {
        boolean found = false;
        for (int i = 0; i < foodLogs.size() && !found; i++) {
            FoodLog log = foodLogs.get(i);
            if (log.getUserID() == userID && log.getFoodID() == foodID && log.getDate().isEqual(date)) {
                foodLogs.set(i, updatedLog);
                found = true;
            }
        }
    }

    @Override
    public void deleteFoodLog(int userID, int foodID, LocalDate date) {
        boolean found = false;
        for (int i = 0; i < foodLogs.size() && !found; i++) {
            FoodLog log = foodLogs.get(i);
            if (log.getUserID() == userID && log.getFoodID() == foodID && log.getDate().isEqual(date)) {
                foodLogs.remove(i);
                found = true;
            }
        }
    }


}
