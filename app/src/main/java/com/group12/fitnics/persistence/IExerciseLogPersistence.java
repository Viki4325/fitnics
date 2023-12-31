package com.group12.fitnics.persistence;

import com.group12.fitnics.objects.ExerciseLog;
import com.group12.fitnics.objects.MyDate;

import java.util.List;

public interface IExerciseLogPersistence {

    ExerciseLog getExerciseLog(final int userID, final int exerciseID, final MyDate date);

    List<ExerciseLog> getExerciseLogByUser(final int userID);

    List<ExerciseLog> getExerciseLogByUserDate(final int userID, final MyDate date);

    void insertExerciseLog(final ExerciseLog exerciseLog);

    void updateExerciseLog(final int userID, final int exerciseID, final MyDate date, final ExerciseLog updatedLog);

    void deleteExerciseLog(final int userID, final int exerciseID, final MyDate date);
}
