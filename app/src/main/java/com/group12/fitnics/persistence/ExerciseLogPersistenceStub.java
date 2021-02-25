package com.group12.fitnics.persistence;

import com.group12.fitnics.objects.ExerciseLog;
import com.group12.fitnics.objects.FoodLog;
import com.group12.fitnics.objects.MyDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ExerciseLogPersistenceStub implements ExerciseLogPersistence{

    private List<ExerciseLog> exerciseLogs;

    public ExerciseLogPersistenceStub() {
        exerciseLogs = new ArrayList<>();

        MyDate date1 = new MyDate(new GregorianCalendar(2021, 0, 1));
        MyDate date2 = new MyDate(new GregorianCalendar(2021, 0, 2));

        exerciseLogs.add(new ExerciseLog(0, 3, date1, 10));
        exerciseLogs.add(new ExerciseLog(1, 1, date1, 5));
        exerciseLogs.add(new ExerciseLog(1, 2, date2, 10));
        exerciseLogs.add(new ExerciseLog(2, 3, date1, 5));
        exerciseLogs.add(new ExerciseLog(2, 6, date1, 10));
        exerciseLogs.add(new ExerciseLog(2, 7, date2, 10));
    }


    @Override
    public ExerciseLog getExerciseLog(int userID, int exerciseID, MyDate date) {
        boolean found = false;
        ExerciseLog result = null;
        for (int i = 0; i < exerciseLogs.size() && !found; i++) {
            ExerciseLog log = exerciseLogs.get(i);
            if (log.getUserID() == userID && log.getExerciseID() == exerciseID && log.getDate().equals(date)) {
                found = true;
                result = log;
            }
        }
        return result;
    }

    @Override
    public List<ExerciseLog> getExerciseLogByUser(int userID) {
        List<ExerciseLog> list = new ArrayList<>();
        for (int i = 0; i < exerciseLogs.size(); i++) {
            if (exerciseLogs.get(i).getUserID() == userID)
                list.add(exerciseLogs.get(i));
        }
        return list;
    }

    @Override
    public List<ExerciseLog> getExerciseLogByUserDate(int userID, MyDate date) {
        List<ExerciseLog> list = new ArrayList<>();
        for (int i = 0; i < exerciseLogs.size(); i++) {
            ExerciseLog log = exerciseLogs.get(i);
            if (log.getUserID() == userID && log.getDate().equals(date))
                list.add(log);
        }
        return list;
    }

    @Override
    public String insertExerciseLog(ExerciseLog exerciseLog) {
        if (!checkInvariant(exerciseLog))
            return "Fail";

        exerciseLogs.add(exerciseLog);
        return "Success";
    }

    @Override
    public String updateExerciseLog(int userID, int exerciseID, MyDate date, ExerciseLog updatedLog) {
        if (!checkInvariant(updatedLog))
            return "Fail";

        boolean found = false;
        for (int i = 0; i < exerciseLogs.size() && !found; i++) {
            ExerciseLog log = exerciseLogs.get(i);
            if (log.getUserID() == userID && log.getExerciseID() == exerciseID && log.getDate().equals(date)) {
                exerciseLogs.set(i, updatedLog);
                found = true;
            }
        }
        if (!found)
            return "Fail";

        return "Success";
    }

    @Override
    public String deleteExerciseLog(int userID, int exerciseID, MyDate date) {
        boolean found = false;
        for (int i = 0; i < exerciseLogs.size() && !found; i++) {
            ExerciseLog log = exerciseLogs.get(i);
            if (log.getUserID() == userID && log.getExerciseID() == exerciseID && log.getDate().equals(date)) {
                exerciseLogs.remove(i);
                found = true;
            }
        }
        if (!found)
            return "Fail";

        return "Success";
    }

    private boolean checkInvariant(ExerciseLog exerciseLog) {
        if (exerciseLog == null || exerciseLog.getUserID() < 0 || exerciseLog.getExerciseID() < 0 || exerciseLog.getMinutes() <= 0)
            return false;
        return true;
    }
}
