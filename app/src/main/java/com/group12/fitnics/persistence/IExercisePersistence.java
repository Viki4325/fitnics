package com.group12.fitnics.persistence;

import com.group12.fitnics.objects.Exercise;

import java.util.List;

public interface IExercisePersistence {

    List<Exercise> getExerciseList();

    Exercise getExerciseById(final int exerciseID);

    Exercise getExerciseByName(final String exerciseName);

    List<Exercise> getExerciseByLevel(final String exerciseLevel);

    List<Exercise> getExerciseByCategory(final String exerciseCategory);

    boolean insertExercise(final Exercise newExercise);

    boolean updateExercise(int exerciseID, Exercise currentExercise);

    boolean deleteExercise(final int exerciseID);

    boolean deleteExercise(Exercise currentExercise);
}
