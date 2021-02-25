package com.group12.fitnics.business;

import com.group12.fitnics.application.Services;
import com.group12.fitnics.objects.ExerciseLog;
import com.group12.fitnics.objects.MyDate;
import com.group12.fitnics.persistence.ExerciseLogPersistence;
import com.group12.fitnics.persistence.ExercisePersistence;

import java.util.List;

public class AccessExerciseLogs {

    private ExerciseLogPersistence exerciseLogPersistence;
    private ExercisePersistence exercisePersistence;

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

    public String insertExerciseLog(ExerciseLog exerciseLog) {
        return exerciseLogPersistence.insertExerciseLog(exerciseLog);
    }

    public String updateExerciseLog(int userID, int exerciseID, MyDate date, ExerciseLog updatedLog) {
        return exerciseLogPersistence.updateExerciseLog(userID, exerciseID, date, updatedLog);
    }

    public String deleteExerciseLog(int userID, int exerciseID, MyDate date) {
        return exerciseLogPersistence.deleteExerciseLog(userID, exerciseID, date);
    }

    public int getUserTotalDailyBurned(int userID, MyDate date) {
        List<ExerciseLog> logs = getExerciseLogByUserDate(userID, date);
        int result = 0;
        if (logs.size() > 0)
            result = getUserTotalDailyBurned(logs, exercisePersistence);
        return result;
    }

    private static int getUserTotalDailyBurned(List<ExerciseLog> logs, ExercisePersistence exercises) {
        int total = 0;
        for (int i = 0; i < logs.size(); i++) {
            int mins = logs.get(i).getMinutes();
            int caloriesPerMins = exercises.getExerciseById(logs.get(i).getExerciseID()).getCaloriesBurn();
            total += mins * caloriesPerMins;
        }
        return total;
    }
}
