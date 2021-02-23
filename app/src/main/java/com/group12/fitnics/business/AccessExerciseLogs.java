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

    ExerciseLog getExerciseLog(int userID, int exerciseID, MyDate date) {
        return exerciseLogPersistence.getExerciseLog(userID, exerciseID, date);
    }

    List<ExerciseLog> getExerciseLogByUser(int userID) {
        return exerciseLogPersistence.getExerciseLogByUser(userID);
    }

    List<ExerciseLog> getExerciseLogByUserDate(int userID, MyDate date) {
        return exerciseLogPersistence.getExerciseLogByUserDate(userID, date);
    }

    String insertExerciseLog(ExerciseLog exerciseLog) {
        return exerciseLogPersistence.insertExerciseLog(exerciseLog);
    }

    String updateExerciseLog(int userID, int exerciseID, MyDate date, ExerciseLog updatedLog) {
        return exerciseLogPersistence.updateExerciseLog(userID, exerciseID, date, updatedLog);
    }

    String deleteExerciseLog(int userID, int exerciseID, MyDate date) {
        return exerciseLogPersistence.deleteExerciseLog(userID, exerciseID, date);
    }

    public int getUserTotalDailyBurned(int userID, MyDate date) {
        List<ExerciseLog> logs = getExerciseLogByUserDate(userID, date);
        int total = CalorieHelper.getUserTotalDailyBurned(logs, exercisePersistence);
        return total;
    }
}
