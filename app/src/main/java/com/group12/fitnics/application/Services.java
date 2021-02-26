package com.group12.fitnics.application;

import com.group12.fitnics.persistence.IExerciseLogPersistence;
import com.group12.fitnics.persistence.ExerciseLogPersistenceStub;
import com.group12.fitnics.persistence.IExercisePersistence;
import com.group12.fitnics.persistence.ExercisePersistenceStub;
import com.group12.fitnics.persistence.IFoodLogPersistence;
import com.group12.fitnics.persistence.FoodLogPersistenceStub;
import com.group12.fitnics.persistence.IFoodPersistence;
import com.group12.fitnics.persistence.FoodPersistenceStub;
import com.group12.fitnics.persistence.IUserPersistence;
import com.group12.fitnics.persistence.UserPersistenceStub;

public class Services {

    private static IUserPersistence userPersistence = null;
    private static IFoodLogPersistence foodLogPersistence = null;
    private static IExerciseLogPersistence exerciseLogPersistence = null;
    private static IFoodPersistence foodPersistence = null;
    private static IExercisePersistence exercisePersistence = null;

    public static IUserPersistence getUserPersistence() {
        if (userPersistence == null) {
            userPersistence = new UserPersistenceStub();
        }
        return userPersistence;
    }

    public static IFoodLogPersistence getFoodLogPersistence() {
        if (foodLogPersistence == null) {
            foodLogPersistence = new FoodLogPersistenceStub();
        }
        return foodLogPersistence;
    }

    public static IExerciseLogPersistence getExerciseLogPersistence() {
        if (exerciseLogPersistence == null) {
            exerciseLogPersistence = new ExerciseLogPersistenceStub();
        }
        return exerciseLogPersistence;
    }

    public static IFoodPersistence getFoodPersistence() {
        if (foodPersistence == null) {
            foodPersistence = new FoodPersistenceStub();
        }
        return foodPersistence;
    }

    public static IExercisePersistence getExercisePersistence() {
        if (exercisePersistence == null) {
            exercisePersistence = new ExercisePersistenceStub();
        }
        return exercisePersistence;
    }

}