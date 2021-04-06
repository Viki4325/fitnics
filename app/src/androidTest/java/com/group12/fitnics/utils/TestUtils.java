package com.group12.fitnics.utils;

import com.group12.fitnics.application.Main;
import com.group12.fitnics.business.AccessExerciseLogs;
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
    private AccessFoodLogs accessFoodLogs;
    private AccessExerciseLogs accessExerciseLogs;

    public TestUtils() {
        // does it work..?
        String path = Main.getDBPathName();
        this.accessUsers = new AccessUsers(new UserPersistenceHSQLDB(path));
        this.accessFoodLogs = new AccessFoodLogs(new FoodLogPersistenceHSQLDB(path), new FoodPersistenceHSQLDB(path));
        this.accessExerciseLogs = new AccessExerciseLogs(new ExerciseLogPersistenceHSQLDB(path), new ExercisePersistenceHSQLDB(path));

    }

    public void deleteUser(String username) {
        int id = accessUsers.getUserByName(username).getUserID();
        accessUsers.deleteUser(id);
    }

    public void updateUser(String username, User user) {
        int id = accessUsers.getUserByName(username).getUserID();
        accessUsers.updateUser(id, user);
    }

    public void deleteFoodLog(int userID, int foodID, LocalDate date) {
        accessFoodLogs.deleteFoodLog(userID, foodID, date);
    }

    public void deleteExerciseLog(int userID, int exerciseID, LocalDate date) {
        accessExerciseLogs.deleteExerciseLog(userID, exerciseID, date);
    }
}
