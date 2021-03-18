package com.group12.fitnics.persistence;

import com.group12.fitnics.objects.ExerciseLog;
import com.group12.fitnics.objects.MyDate;

import java.time.LocalDate;
import java.util.List;

public interface IExerciseLogPersistence {

    ExerciseLog getExerciseLog(final int userID, final int exerciseID, final LocalDate date);

    List<ExerciseLog> getExerciseLogByUser(final int userID);

    List<ExerciseLog> getExerciseLogByUserDate(final int userID, final LocalDate date);

    void insertExerciseLog(final ExerciseLog exerciseLog);

    void updateExerciseLog(final int userID, final int exerciseID, final LocalDate date, final ExerciseLog updatedLog);

    void deleteExerciseLog(final int userID, final int exerciseID, final LocalDate date);

    default boolean checkInvariant(ExerciseLog exerciseLog) {
        if (exerciseLog == null || exerciseLog.getUserID() < 0 || exerciseLog.getExerciseID() < 0 || exerciseLog.getMinutes() <= 0)
            return false;
        return true;
    }
}
