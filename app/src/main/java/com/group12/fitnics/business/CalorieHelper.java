package com.group12.fitnics.business;

import com.group12.fitnics.objects.ExerciseLog;
import com.group12.fitnics.objects.FoodLog;
import com.group12.fitnics.persistence.ExercisePersistence;
import com.group12.fitnics.persistence.FoodPersistence;

import java.util.List;

public class CalorieHelper {

    public static int getUserTotalDailyIntake(List<FoodLog> logs, FoodPersistence foods) {
        int total = 0;
        for (int i = 0; i < logs.size(); i++) {
            int gram = logs.get(i).getGrams();
            int caloriesPerGram = foods.getFoodByID(logs.get(i).getFoodID()).getCalories();
            total += gram * caloriesPerGram;
        }
        return total;
    }

    public static int getUserTotalDailyBurned(List<ExerciseLog> logs, ExercisePersistence exercises) {
        int total = 0;
        for (int i = 0; i < logs.size(); i++) {
            int mins = logs.get(i).getMinutes();
            int caloriesPerMins = exercises.getExerciseById(logs.get(i).getExerciseID()).getCaloriesBurn();
            total += mins * caloriesPerMins;
        }
        return total;
    }
}
