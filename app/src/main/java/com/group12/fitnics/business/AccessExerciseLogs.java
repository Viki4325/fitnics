package com.group12.fitnics.business;

import com.group12.fitnics.application.Services;
import com.group12.fitnics.exceptions.ExerciseLogNotFoundException;
import com.group12.fitnics.exceptions.InvalidExerciseLogException;
import com.group12.fitnics.objects.ExerciseLog;
import com.group12.fitnics.persistence.IExerciseLogPersistence;
import com.group12.fitnics.persistence.IExercisePersistence;

import java.time.LocalDate;
import java.util.List;

public class AccessExerciseLogs {

    private IExerciseLogPersistence exerciseLogPersistence;
    private IExercisePersistence exercisePersistence;

    public AccessExerciseLogs(IExerciseLogPersistence exerciseLogPersistence, IExercisePersistence exercisePersistence) {
        this.exerciseLogPersistence = exerciseLogPersistence;
        this.exercisePersistence = exercisePersistence;
    }

    public AccessExerciseLogs() {
        exerciseLogPersistence = Services.getExerciseLogPersistence();
        exercisePersistence = Services.getExercisePersistence();
    }

    public ExerciseLog getExerciseLog(int userID, int exerciseID, LocalDate date) {
        return exerciseLogPersistence.getExerciseLog(userID, exerciseID, date);
    }

    public List<ExerciseLog> getExerciseLogByUser(int userID) {
        return exerciseLogPersistence.getExerciseLogByUser(userID);
    }

    public List<ExerciseLog> getExerciseLogByUserDate(int userID, LocalDate date) {
        return exerciseLogPersistence.getExerciseLogByUserDate(userID, date);
    }

    public void insertExerciseLog(ExerciseLog exerciseLog) throws InvalidExerciseLogException {
        if (!checkInvariant(exerciseLog))
            throw new InvalidExerciseLogException("The exercise log has invalid userID or exerciseID or minutes. ");

        // if there exists same exercise log already, to not allow it to be inserted
        if (getExerciseLog(exerciseLog.getUserID(), exerciseLog.getExerciseID(), exerciseLog.getDate()) != null)
            throw new InvalidExerciseLogException("The exercise log is duplicate. You could instead increase the time for this exercise from the logs. Thank you!");

        exerciseLogPersistence.insertExerciseLog(exerciseLog);
    }

    public void updateExerciseLog(int userID, int exerciseID, LocalDate date, ExerciseLog updatedLog) throws InvalidExerciseLogException, ExerciseLogNotFoundException {
        if (!checkInvariant(updatedLog))
            throw new InvalidExerciseLogException("The exercise log has invalid userID or exerciseID or minutes. ");

        if (getExerciseLog(userID, exerciseID, date) == null)
            throw new ExerciseLogNotFoundException("There is no such exercise log to update. ");

        exerciseLogPersistence.updateExerciseLog(userID, exerciseID, date, updatedLog);
    }

    public void deleteExerciseLog(int userID, int exerciseID, LocalDate date) throws ExerciseLogNotFoundException {
        if (getExerciseLog(userID, exerciseID, date) == null)
            throw new ExerciseLogNotFoundException("There is no such exercise log to update. ");

        exerciseLogPersistence.deleteExerciseLog(userID, exerciseID, date);
    }

    public int getUserTotalDailyBurned(int userID, LocalDate date) {
        List<ExerciseLog> logs = getExerciseLogByUserDate(userID, date);
        int result = 0;
        if (logs.size() > 0)
            result = getUserTotalDailyBurned(logs, exercisePersistence);
        return result;
    }

    private static int getUserTotalDailyBurned(List<ExerciseLog> logs, IExercisePersistence exercises) {
        int total = 0;
        for (int i = 0; i < logs.size(); i++) {
            int mins = logs.get(i).getMinutes();
            int caloriesPerMins = exercises.getExerciseById(logs.get(i).getExerciseID()).getCaloriesBurn();
            total += mins * caloriesPerMins;
        }
        return total;
    }

    private boolean checkInvariant(ExerciseLog exerciseLog) {
        if (exerciseLog == null || exerciseLog.getUserID() < 0 || exerciseLog.getExerciseID() < 0 || exerciseLog.getMinutes() <= 0)
            return false;
        return true;
    }
}
