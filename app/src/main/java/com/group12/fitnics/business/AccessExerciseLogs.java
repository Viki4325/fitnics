package com.group12.fitnics.business;

import com.group12.fitnics.application.Services;
import com.group12.fitnics.exceptions.ExerciseLogNotFoundException;
import com.group12.fitnics.exceptions.InvalidExerciseLogException;
import com.group12.fitnics.objects.ExerciseLog;
import com.group12.fitnics.objects.MyDate;
import com.group12.fitnics.persistence.IExerciseLogPersistence;
import com.group12.fitnics.persistence.IExercisePersistence;

import java.util.List;

public class AccessExerciseLogs {

    private IExerciseLogPersistence exerciseLogPersistence;
    private IExercisePersistence exercisePersistence;

    public AccessExerciseLogs() {
        exerciseLogPersistence = Services.getExerciseLogPersistence();
        exercisePersistence = Services.getExercisePersistence();
    }

    public ExerciseLog getExerciseLog(int userID, int exerciseID, MyDate date) {
        return exerciseLogPersistence.getExerciseLog(userID, exerciseID, date);
    }

    public List<ExerciseLog> getExerciseLogByUser(int userID) {
        return exerciseLogPersistence.getExerciseLogByUser(userID);
    }

    public List<ExerciseLog> getExerciseLogByUserDate(int userID, MyDate date) {
        return exerciseLogPersistence.getExerciseLogByUserDate(userID, date);
    }

    public void insertExerciseLog(ExerciseLog exerciseLog) throws InvalidExerciseLogException {
        exerciseLogPersistence.insertExerciseLog(exerciseLog);
    }

    public void updateExerciseLog(int userID, int exerciseID, MyDate date, ExerciseLog updatedLog) throws InvalidExerciseLogException, ExerciseLogNotFoundException {
        exerciseLogPersistence.updateExerciseLog(userID, exerciseID, date, updatedLog);
    }

    public void deleteExerciseLog(int userID, int exerciseID, MyDate date) throws ExerciseLogNotFoundException {
        exerciseLogPersistence.deleteExerciseLog(userID, exerciseID, date);
    }

    public int getUserTotalDailyBurned(int userID, MyDate date) {
        List<ExerciseLog> logs = getExerciseLogByUserDate(userID, date);
        int result = 0;
        if (logs.size() > 0)
            result = getUserTotalDailyBurned(logs, exercisePersistence);
        return result;
    }

    private static int getUserTotalDailyBurned(List<ExerciseLog> logs, IExercisePersistence exercises) {
        int total = 0;
        for (int i = 0; i < logs.size(); i++) {
            int mins = logs.get(0).getMinutes();
            int caloriesPerMins = exercises.getExerciseById(logs.get(i).getExerciseID()).getCaloriesBurn();
            total += mins * caloriesPerMins;
        }
        return total;
    }
}
