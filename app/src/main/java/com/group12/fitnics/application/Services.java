package com.group12.fitnics.application;

import com.group12.fitnics.objects.Exercise;
import com.group12.fitnics.objects.ExerciseLog;
import com.group12.fitnics.persistence.ExerciseLogPersistence;
import com.group12.fitnics.persistence.ExerciseLogPersistenceStub;
import com.group12.fitnics.persistence.ExercisePersistence;
import com.group12.fitnics.persistence.FoodLogPersistence;
import com.group12.fitnics.persistence.FoodLogPersistenceStub;
import com.group12.fitnics.persistence.FoodPersistence;
import com.group12.fitnics.persistence.UserPersistence;
import com.group12.fitnics.persistence.UserPersistenceStub;

public class Services {

    private static UserPersistence userPersistence = null;
    private static FoodLogPersistence foodLogPersistence = null;
    private static ExerciseLogPersistence exerciseLogPersistence = null;
    private static FoodPersistence foodPersistence = null;
    private static ExercisePersistence exercisePersistence = null

    public static UserPersistence getUserPersistence() {
        if (userPersistence == null) {
            userPersistence = new UserPersistenceStub();
        }
        return userPersistence;
    }

    public static FoodLogPersistence getFoodLogPersistence() {
        if (foodLogPersistence == null) {
            foodLogPersistence = new FoodLogPersistenceStub();
        }
        return foodLogPersistence;
    }

    public static ExerciseLogPersistence getExerciseLogPersistence() {
        if (exerciseLogPersistence == null) {
            exerciseLogPersistence = new ExerciseLogPersistenceStub();
        }
        return exerciseLogPersistence;
    }

    public static FoodPersistence getFoodPersistence() {
        if (foodPersistence == null) {
            foodPersistence = new FoodPersistenceStub();
        }
        return foodPersistence;
    }

    public static ExercisePersistence getExercisePersistence() {
        if (exercisePersistence == null) {
            exercisePersistence = new ExercisePersistenceStub();
        }
        return exercisePersistence;
    }

}