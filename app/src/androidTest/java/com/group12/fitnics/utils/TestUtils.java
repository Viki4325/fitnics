package com.group12.fitnics.utils;

import com.group12.fitnics.application.Main;
import com.group12.fitnics.business.AccessExerciseLogs;
import com.group12.fitnics.business.AccessExercises;
import com.group12.fitnics.business.AccessFood;
import com.group12.fitnics.business.AccessFoodLogs;
import com.group12.fitnics.business.AccessUsers;
import com.group12.fitnics.objects.User;
import com.group12.fitnics.persistence.hsqldb.ExerciseLogPersistenceHSQLDB;
import com.group12.fitnics.persistence.hsqldb.ExercisePersistenceHSQLDB;
import com.group12.fitnics.persistence.hsqldb.FoodLogPersistenceHSQLDB;
import com.group12.fitnics.persistence.hsqldb.FoodPersistenceHSQLDB;
import com.group12.fitnics.persistence.hsqldb.UserPersistenceHSQLDB;

import java.time.LocalDate;

public class TestUtils {
    private AccessUsers accessUsers;
    private AccessFood accessFood;
    private AccessExercises accessExercises;
    private AccessFoodLogs accessFoodLogs;
    private AccessExerciseLogs accessExerciseLogs;

    public TestUtils() {
        this.accessUsers = new AccessUsers();
        this.accessFood = new AccessFood();
        this.accessExercises = new AccessExercises();
        this.accessFoodLogs = new AccessFoodLogs();
        this.accessExerciseLogs = new AccessExerciseLogs();

    }

    public void deleteUser(String username) {
        int id = accessUsers.getUserByName(username).getUserID();
        accessUsers.deleteUser(id);
    }

    public void updateUser(String username, User user) {
        int id = accessUsers.getUserByName(username).getUserID();
        accessUsers.updateUser(id, user);
    }

    public void deleteFoodLog(String username, String foodName, LocalDate date) {
        int id = accessUsers.getUserByName(username).getUserID();
        int fid = accessFood.searchByFoodName(foodName).getFoodID();
        accessFoodLogs.deleteFoodLog(id, fid, date);
    }

    public void deleteExerciseLog(String username, String exerciseName, LocalDate date) {
        int id = accessUsers.getUserByName(username).getUserID();
        int eid = accessExercises.getExerciseByName(exerciseName).getExerciseID();
        accessExerciseLogs.deleteExerciseLog(id, eid, date);
    }
}
