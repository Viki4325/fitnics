package com.group12.fitnics.persistence.hsqldb;

import com.group12.fitnics.objects.ExerciseLog;
import com.group12.fitnics.persistence.IExerciseLogPersistence;

import java.time.LocalDate;
import java.util.List;

public class ExerciseLogPersistenceHSQLDB implements IExerciseLogPersistence {


    @Override
    public ExerciseLog getExerciseLog(int userID, int exerciseID, LocalDate date) {
        return null;
    }

    @Override
    public List<ExerciseLog> getExerciseLogByUser(int userID) {
        return null;
    }

    @Override
    public List<ExerciseLog> getExerciseLogByUserDate(int userID, LocalDate date) {
        return null;
    }

    @Override
    public void insertExerciseLog(ExerciseLog exerciseLog) {

    }

    @Override
    public void updateExerciseLog(int userID, int exerciseID, LocalDate date, ExerciseLog updatedLog) {

    }

    @Override
    public void deleteExerciseLog(int userID, int exerciseID, LocalDate date) {

    }
}
