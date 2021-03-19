package com.group12.fitnics.application;

import com.group12.fitnics.persistence.ExerciseLogPersistenceStub;
import com.group12.fitnics.persistence.ExercisePersistenceStub;
import com.group12.fitnics.persistence.FoodLogPersistenceStub;
import com.group12.fitnics.persistence.FoodPersistenceStub;
import com.group12.fitnics.persistence.IExerciseLogPersistence;
import com.group12.fitnics.persistence.IExercisePersistence;
import com.group12.fitnics.persistence.IFoodLogPersistence;
import com.group12.fitnics.persistence.IFoodPersistence;
import com.group12.fitnics.persistence.IUserPersistence;
import com.group12.fitnics.persistence.UserPersistenceStub;
import com.group12.fitnics.persistence.hsqldb.ExerciseLogPersistenceHSQLDB;
import com.group12.fitnics.persistence.hsqldb.ExercisePersistenceHSQLDB;
import com.group12.fitnics.persistence.hsqldb.FoodLogPersistenceHSQLDB;
import com.group12.fitnics.persistence.hsqldb.FoodPersistenceHSQLDB;
import com.group12.fitnics.persistence.hsqldb.UserPersistenceHSQLDB;

public class Services {

    private static IUserPersistence userPersistence = null;
    private static IFoodLogPersistence foodLogPersistence = null;
    private static IExerciseLogPersistence exerciseLogPersistence = null;
    private static IFoodPersistence foodPersistence = null;
    private static IExercisePersistence exercisePersistence = null;

    private static final boolean forProduction = true;

    public static IUserPersistence getUserPersistence() {
        if (userPersistence == null) {
            if (forProduction) {
                userPersistence = new UserPersistenceHSQLDB(Main.getDBPathName());
            } else {
                userPersistence = new UserPersistenceStub();
            }
        }
        return userPersistence;
    }

    public static IFoodLogPersistence getFoodLogPersistence() {
        if (foodLogPersistence == null) {
            if (forProduction) {
                foodLogPersistence = new FoodLogPersistenceHSQLDB(Main.getDBPathName());
            } else {
                foodLogPersistence = new FoodLogPersistenceStub();
            }
        }
        return foodLogPersistence;
    }

    public static IExerciseLogPersistence getExerciseLogPersistence() {
        if (exerciseLogPersistence == null) {
            if (forProduction) {
                exerciseLogPersistence = new ExerciseLogPersistenceHSQLDB(Main.getDBPathName());
            } else {
                exerciseLogPersistence = new ExerciseLogPersistenceStub();
            }
        }
        return exerciseLogPersistence;
    }

    public static IFoodPersistence getFoodPersistence() {
        if (foodPersistence == null) {
            if (forProduction) {
                foodPersistence = new FoodPersistenceHSQLDB(Main.getDBPathName());
            } else {
                foodPersistence = new FoodPersistenceStub();
            }
        }
        return foodPersistence;
    }

    public static IExercisePersistence getExercisePersistence() {
        if (exercisePersistence == null) {
            if (forProduction) {
                exercisePersistence = new ExercisePersistenceHSQLDB(Main.getDBPathName());
            } else {
                exercisePersistence = new ExercisePersistenceStub();
            }
        }
        return exercisePersistence;
    }

}