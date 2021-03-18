package com.group12.fitnics.persistence.hsqldb;

import com.group12.fitnics.business.DateHelper;
import com.group12.fitnics.exceptions.FoodLogNotFoundException;
import com.group12.fitnics.exceptions.InvalidFoodLogException;
import com.group12.fitnics.objects.FoodLog;
import com.group12.fitnics.persistence.IFoodLogPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
        int grams = rs.getInt("grams");
        return new FoodLog(userId, foodId, dateStr, grams);
    }

    @Override
    public FoodLog getFoodLog(int userID, int foodID, LocalDate date) {
        try (Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM FOODLOGS WHERE uid = ? AND fid = ? AND date = ?");
            st.setString(1, Integer.toString(userID));
            st.setString(2, Integer.toString(foodID));
            st.setString(3, DateHelper.dateToString(date));
            final ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return fromResultSet(rs);
            }
            rs.close();
            st.close();

        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<FoodLog> getFoodLogByUser(int userID) {
        List<FoodLog> logs = new ArrayList<>();
        try (Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM FOODLOGS WHERE uid = ?");
            st.setString(1, Integer.toString(userID));
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final FoodLog foodLog = fromResultSet(rs);
                logs.add(foodLog);
            }
            rs.close();
            st.close();

        } catch (final SQLException e) {
            e.printStackTrace();
        }

        return logs;
    }

    @Override
    public List<FoodLog> getFoodLogByUserDate(int userID, LocalDate date) {
        List<FoodLog> logs = new ArrayList<>();
        try (Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM FOODLOGS WHERE uid = ? AND date = ?");
            st.setString(1, Integer.toString(userID));
            st.setString(2, DateHelper.dateToString(date));
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final FoodLog foodLog = fromResultSet(rs);
                logs.add(foodLog);
            }
            rs.close();
            st.close();

        } catch (final SQLException e) {
            e.printStackTrace();
        }

        return logs;
    }

    @Override
    public void insertFoodLog(FoodLog foodLog) throws InvalidFoodLogException {
        if (!checkInvariant(foodLog))
            throw new InvalidFoodLogException("The food log has invalid userID or foodID or grams. ");

        // if there exists same food log already, to not allow it to be inserted
        if (getFoodLog(foodLog.getUserID(), foodLog.getFoodID(), foodLog.getDate()) != null)
            throw new InvalidFoodLogException("The food log is duplicate. ");

        try (final Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO FOODLOGS VALUES(?, ?, ?, ?)");
            st.setInt(1, foodLog.getUserID());
            st.setInt(2, foodLog.getFoodID());
            st.setString(3, foodLog.getDateString());
            st.setInt(4, foodLog.getGrams());
            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFoodLog(int userID, int foodID, LocalDate date, FoodLog updatedLog) throws InvalidFoodLogException, FoodLogNotFoundException {
        if (!checkInvariant(updatedLog))
            throw new InvalidFoodLogException("The food log has invalid userID or foodID or grams. ");

        if (getFoodLog(userID, foodID, date) == null)
            throw new FoodLogNotFoundException("There is no such food log to update. ");

        try (final Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("UPDATE FOODLOGS SET uid=?, fid=?, date=?, grams=? where uid=? AND fid=? AND date=?");
            st.setInt(1, updatedLog.getUserID());
            st.setInt(2, updatedLog.getFoodID());
            st.setString(3, updatedLog.getDateString());
            st.setInt(4, updatedLog.getGrams());
            st.setString(5, Integer.toString(userID));
            st.setString(6, Integer.toString(foodID));
            st.setString(7, DateHelper.dateToString(date));
            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFoodLog(int userID, int foodID, LocalDate date) throws FoodLogNotFoundException {
        if (getFoodLog(userID, foodID, date) == null)
            throw new FoodLogNotFoundException("There is no such food log to update. ");

        try (final Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("DELETE FROM FOODLOGS WHERE uid = ? AND fid = ? AND date = ?");
            st.setString(1, Integer.toString(userID));
            st.setString(2, Integer.toString(foodID));
            st.setString(3, DateHelper.dateToString(date));
            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

}
