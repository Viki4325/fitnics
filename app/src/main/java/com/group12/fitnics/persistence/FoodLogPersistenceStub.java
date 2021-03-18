package com.group12.fitnics.persistence;

import com.group12.fitnics.exceptions.FoodLogNotFoundException;
import com.group12.fitnics.exceptions.InvalidFoodLogException;
import com.group12.fitnics.objects.FoodLog;
import com.group12.fitnics.objects.MyDate;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class FoodLogPersistenceStub implements IFoodLogPersistence {
    private List<FoodLog> foodLogs;

    public FoodLogPersistenceStub() {
        foodLogs = new ArrayList<>();

        MyDate date1 = new MyDate(new GregorianCalendar(2021, 0, 1));
        MyDate date2 = new MyDate(new GregorianCalendar(2021, 0, 2));
        MyDate date3 = new MyDate(new GregorianCalendar(2021, 1, 17));

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
    public FoodLog getFoodLog(int userID, int foodID, MyDate date) {
        boolean found = false;
        FoodLog result = null;
        for (int i = 0; i < foodLogs.size() && !found; i++) {
            FoodLog log = foodLogs.get(i);
            if (log.getUserID() == userID && log.getFoodID() == foodID && log.getDate().equals(date)) {
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
    public List<FoodLog> getFoodLogByUserDate(int userID, MyDate date) {
        List<FoodLog> list = new ArrayList<>();
        for (int i = 0; i < foodLogs.size(); i++) {
            FoodLog log = foodLogs.get(i);
            if (log.getUserID() == userID && log.getDate().equals(date))
                list.add(log);
        }
        return list;
    }

    @Override
    public void insertFoodLog(FoodLog foodLog) throws InvalidFoodLogException {
        if (!checkInvariant(foodLog))
            throw new InvalidFoodLogException("The food log has invalid userID or foodID or grams. ");

        // if there exists same food log already, to not allow it to be inserted
        if (getFoodLog(foodLog.getUserID(), foodLog.getFoodID(), foodLog.getDate()) != null)
            throw new InvalidFoodLogException("The food log is duplicate. \n You can instead go to FoodLog and add extra amount of grams consumed. Thank you!");

        foodLogs.add(foodLog);
    }

    @Override
    public void updateFoodLog(int userID, int foodID, MyDate date, FoodLog updatedLog) throws InvalidFoodLogException, FoodLogNotFoundException {
        if (!checkInvariant(updatedLog))
            throw new InvalidFoodLogException("The food log has invalid userID or foodID or grams. ");

        boolean found = false;
        for (int i = 0; i < foodLogs.size() && !found; i++) {
            FoodLog log = foodLogs.get(i);
            if (log.getUserID() == userID && log.getFoodID() == foodID && log.getDate().equals(date)) {
                foodLogs.set(i, updatedLog);
                found = true;
            }
        }
        if (!found)
            throw new FoodLogNotFoundException("There is no such food log to update. ");
    }

    @Override
    public void deleteFoodLog(int userID, int foodID, MyDate date) throws FoodLogNotFoundException {
        boolean found = false;
        for (int i = 0; i < foodLogs.size() && !found; i++) {
            FoodLog log = foodLogs.get(i);
            if (log.getUserID() == userID && log.getFoodID() == foodID && log.getDate().equals(date)) {
                foodLogs.remove(i);
                found = true;
            }
        }
        if (!found)
            throw new FoodLogNotFoundException("There is no such food log to delete. ");
    }

    private boolean checkInvariant(FoodLog foodLog) {
        if (foodLog == null || foodLog.getUserID() < 0 || foodLog.getFoodID() < 0 || foodLog.getGrams() <= 0)
            return false;
        return true;
    }
}
