package com.group12.fitnics.persistence;

import com.group12.fitnics.objects.Exercise;

import java.util.List;

public interface IExercisePersistence {

    List<Exercise> getExerciseList();

    Exercise getExerciseById(final int exerciseID);

    Exercise getExerciseByName(final String exerciseName);

    List<Exercise> getExerciseByLevel(final String exerciseLevel);

    List<Exercise> getExerciseByCategory(final String exerciseCategory);

    int insertExercise(final Exercise newExercise);

    void updateExercise(int exerciseID, Exercise currentExercise);

    void deleteExercise(final int exerciseID);

    void deleteExercise(Exercise currentExercise);
}
