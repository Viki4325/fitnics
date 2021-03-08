package com.group12.fitnics.persistence.hsqldb;

import com.group12.fitnics.objects.Exercise;
import com.group12.fitnics.persistence.IExercisePersistence;

import java.util.List;

public class ExercisePersistenceHSQLDB implements IExercisePersistence {
    @Override
    public List<Exercise> getExerciseList() {
        return null;
    }

    @Override
    public Exercise getExerciseById(int exerciseID) {
        return null;
    }

    @Override
    public Exercise getExerciseByName(String exerciseName) {
        return null;
    }

    @Override
    public List<Exercise> getExerciseByLevel(String exerciseLevel) {
        return null;
    }

    @Override
    public List<Exercise> getExerciseByCategory(String exerciseCategory) {
        return null;
    }

    @Override
    public void insertExercise(Exercise newExercise) {

    }

    @Override
    public void updateExercise(int exerciseID, Exercise currentExercise) {

    }

    @Override
    public void deleteExercise(int exerciseID) {

    }

    @Override
    public void deleteExercise(Exercise currentExercise) {

    }
}
