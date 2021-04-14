package com.group12.fitnics.persistence.hsqldb;

import com.group12.fitnics.business.DateHelper;
import com.group12.fitnics.exceptions.HSQLDBException;
import com.group12.fitnics.objects.ExerciseLog;
import com.group12.fitnics.persistence.IExerciseLogPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExerciseLogPersistenceHSQLDB implements IExerciseLogPersistence {

    private final String dbPath;

    public ExerciseLogPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private ExerciseLog fromResultSet(ResultSet rs) throws SQLException {
        int userId = rs.getInt("uid");
        int exerciseID = rs.getInt("eid");
        String dateStr = rs.getString("date");
        int minutes = rs.getInt("minutes");
        return new ExerciseLog(userId, exerciseID, dateStr, minutes);
    }

    @Override
    public ExerciseLog getExerciseLog(int userID, int exerciseID, LocalDate date) {
        try (Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM EXERCISELOGS WHERE uid = ? AND eid = ? AND date = ?");
            st.setString(1, Integer.toString(userID));
            st.setString(2, Integer.toString(exerciseID));
            st.setString(3, DateHelper.dateToString(date));
            final ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return fromResultSet(rs);
            }
            rs.close();
            st.close();

        } catch (final SQLException e) {
            throw new HSQLDBException(e);
        }
        return null;
    }

    @Override
    public List<ExerciseLog> getExerciseLogByUser(int userID) {
        List<ExerciseLog> logs = new ArrayList<>();
        try (Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM EXERCISELOGS WHERE uid = ?");
            st.setString(1, Integer.toString(userID));
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final ExerciseLog exerciseLog = fromResultSet(rs);
                logs.add(exerciseLog);
            }
            rs.close();
            st.close();

        } catch (final SQLException e) {
            throw new HSQLDBException(e);
        }

        return logs;
    }

    @Override
    public List<ExerciseLog> getExerciseLogByUserDate(int userID, LocalDate date) {
        List<ExerciseLog> logs = new ArrayList<>();
        try (Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM EXERCISELOGS WHERE uid = ?AND date = ?");
            st.setString(1, Integer.toString(userID));
            st.setString(2, DateHelper.dateToString(date));
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final ExerciseLog exerciseLog = fromResultSet(rs);
                logs.add(exerciseLog);
            }
            rs.close();
            st.close();

        } catch (final SQLException e) {
            throw new HSQLDBException(e);
        }

        return logs;
    }

    @Override
    public void insertExerciseLog(ExerciseLog exerciseLog) {

        try (final Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO EXERCISELOGS VALUES(?, ?, ?, ?)");
            st.setInt(1, exerciseLog.getUserID());
            st.setInt(2, exerciseLog.getExerciseID());
            st.setString(3, exerciseLog.getDateString());
            st.setInt(4, exerciseLog.getMinutes());
            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            throw new HSQLDBException(e);
        }
    }

    @Override
    public void updateExerciseLog(int userID, int exerciseID, LocalDate date, ExerciseLog updatedLog) {

        try (final Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("UPDATE EXERCISELOGS SET uid=?, eid=?, date=?, minutes=? where uid=? AND eid=? AND date=?");
            st.setInt(1, updatedLog.getUserID());
            st.setInt(2, updatedLog.getExerciseID());
            st.setString(3, updatedLog.getDateString());
            st.setInt(4, updatedLog.getMinutes());
            st.setString(5, Integer.toString(userID));
            st.setString(6, Integer.toString(exerciseID));
            st.setString(7, DateHelper.dateToString(date));
            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            throw new HSQLDBException(e);
        }
    }

    @Override
    public void deleteExerciseLog(int userID, int exerciseID, LocalDate date) {

        try (final Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("DELETE FROM EXERCISELOGS WHERE uid = ? AND eid = ? AND date = ?");
            st.setString(1, Integer.toString(userID));
            st.setString(2, Integer.toString(exerciseID));
            st.setString(3, DateHelper.dateToString(date));
            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            throw new HSQLDBException(e);
        }
    }
}
