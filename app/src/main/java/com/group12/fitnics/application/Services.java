package com.group12.fitnics.application;

import com.group12.fitnics.persistence.ExercisePersistence;
import com.group12.fitnics.persistence.ExercisePersistenceStub;
import com.group12.fitnics.persistence.UserPersistence;
import com.group12.fitnics.persistence.UserPersistenceStub;

public class Services {

    private static UserPersistence userPersistence = null;
    private static ExercisePersistence exercisePersistence = null;

    public static UserPersistence getUserPersistence() {
        if (userPersistence == null) {
            userPersistence = new UserPersistenceStub();
        }
        return userPersistence;
    }

    public static ExercisePersistence getExercisePersistence() {
        if (exercisePersistence == null) {
            exercisePersistence = new ExercisePersistenceStub();
        }
        return exercisePersistence;
    }


}