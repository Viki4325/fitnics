package com.group12.fitnics.persistence.hsqldb;

import com.group12.fitnics.objects.FoodLog;
import com.group12.fitnics.persistence.IFoodLogPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class FoodLogPersistenceHSQLDB implements IFoodLogPersistence {

    private final String dbPath;

    public FoodLogPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private FoodLog fromResultSet(ResultSet rs) throws SQLException {
        int userId = rs.getInt("uid");
        int foodId = rs.getInt("fid");
        String dateStr = rs.getString("date");
        int gram = rs.getInt("gram");
        return new FoodLog(userId, foodId, dateStr, gram);
    }

    @Override
    public FoodLog getFoodLog(int userID, int exerciseID, LocalDate date) {
        return null;
    }

    @Override
    public List<FoodLog> getFoodLogByUser(int userID) {
        return null;
    }

    @Override
    public List<FoodLog> getFoodLogByUserDate(int userID, LocalDate date) {
        return null;
    }

    @Override
    public void insertFoodLog(FoodLog foodLog) {

    }

    @Override
    public void updateFoodLog(int userID, int foodID, LocalDate date, FoodLog updatedLog) {

    }

    @Override
    public void deleteFoodLog(int userID, int foodID, LocalDate date) {

    }
}
