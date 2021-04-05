package com.group12.fitnics.application;

import com.group12.fitnics.persistence.IExerciseLogPersistence;
import com.group12.fitnics.persistence.IExercisePersistence;
import com.group12.fitnics.persistence.IFoodLogPersistence;
import com.group12.fitnics.persistence.IFoodPersistence;
import com.group12.fitnics.persistence.IUserPersistence;
import com.group12.fitnics.persistence.hsqldb.ExerciseLogPersistenceHSQLDB;
import com.group12.fitnics.persistence.hsqldb.ExercisePersistenceHSQLDB;
import com.group12.fitnics.persistence.hsqldb.FoodLogPersistenceHSQLDB;
import com.group12.fitnics.persistence.hsqldb.FoodPersistenceHSQLDB;
import com.group12.fitnics.persistence.hsqldb.UserPersistenceHSQLDB;
import com.group12.fitnics.persistence.stub.ExerciseLogPersistenceStub;
import com.group12.fitnics.persistence.stub.ExercisePersistenceStub;
import com.group12.fitnics.persistence.stub.FoodLogPersistenceStub;
import com.group12.fitnics.persistence.stub.FoodPersistenceStub;
import com.group12.fitnics.persistence.stub.UserPersistenceStub;

public class Services {

    private static IUserPersistence userPersistence = null;
    private static IFoodLogPersistence foodLogPersistence = null;
    private static IExerciseLogPersistence exerciseLogPersistence = null;
    private static IFoodPersistence foodPersistence = null;
    private static IExercisePersistence exercisePersistence = null;

    private static boolean initialized = false;

    public static IUserPersistence getUserPersistence() {
        if (!initialized)
            buildPersistence();
        return userPersistence;
    }

    public static IFoodLogPersistence getFoodLogPersistence() {
        if (!initialized)
            buildPersistence();
        return foodLogPersistence;
    }

    public static IExerciseLogPersistence getExerciseLogPersistence() {
        if (!initialized)
            buildPersistence();
        return exerciseLogPersistence;
    }

    public static IFoodPersistence getFoodPersistence() {
        if (!initialized)
            buildPersistence();
        return foodPersistence;
    }

    public static IExercisePersistence getExercisePersistence() {
        if (!initialized)
            buildPersistence();
        return exercisePersistence;
    }

    private static void buildPersistence() {
        if (!initialized) {
            userPersistence = new UserPersistenceHSQLDB(Main.getDBPathName());
            foodPersistence = new FoodPersistenceHSQLDB(Main.getDBPathName());
            exercisePersistence = new ExercisePersistenceHSQLDB(Main.getDBPathName());
            foodLogPersistence = new FoodLogPersistenceHSQLDB(Main.getDBPathName());
            exerciseLogPersistence = new ExerciseLogPersistenceHSQLDB(Main.getDBPathName());

//            userPersistence = new UserPersistenceStub();
//            foodPersistence = new FoodPersistenceStub();
//            exercisePersistence = new ExercisePersistenceStub();
//            foodLogPersistence = new FoodLogPersistenceStub();
//            exerciseLogPersistence = new ExerciseLogPersistenceStub();
        }

        initialized = true;
    }

}