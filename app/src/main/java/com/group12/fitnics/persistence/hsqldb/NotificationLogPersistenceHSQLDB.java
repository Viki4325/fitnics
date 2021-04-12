package com.group12.fitnics.persistence.hsqldb;




import com.group12.fitnics.objects.NotificationLog;
import com.group12.fitnics.persistence.INotificationLogPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationLogPersistenceHSQLDB implements INotificationLogPersistence {
    private final String dbPath;

    public NotificationLogPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private NotificationLog fromResultSet(ResultSet rs) throws SQLException {
        String title = rs.getString("name");
        int userId = rs.getInt("uid");
        int NotificationID = rs.getInt("nid");
        int hour = rs.getInt("hour");
        int minutes = rs.getInt("minutes");
        return new NotificationLog(title, userId, NotificationID, hour, minutes);
    }

    @Override
    public NotificationLog getNotificationLog(int userID, int NotificationID) {
        try (Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM NOTIFICATIONLOGS WHERE uid = ? AND nid = ?");
            st.setString(1, Integer.toString(userID));
            st.setString(2, Integer.toString(NotificationID));
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
    public List<NotificationLog> getNotificationLogByUser(int userID) {
        List<NotificationLog> logs = new ArrayList<>();
        try (Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM NOTIFICATIONLOGS WHERE uid = ?");
            st.setString(1, Integer.toString(userID));
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final NotificationLog NotificationLog = fromResultSet(rs);
                logs.add(NotificationLog);
            }
            rs.close();
            st.close();

        } catch (final SQLException e) {
            e.printStackTrace();
        }

        return logs;
    }

    @Override
    public void insertNotificationLog(NotificationLog NotificationLog) {
        try (final Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO NOTIFICATIONLOGS VALUES(?, ?, ?, ?, ?)");
            st.setString(1, NotificationLog.getTitle());
            st.setInt(2, NotificationLog.getUserID());
            st.setInt(3, NotificationLog.getNotificationID());
            st.setInt(4, NotificationLog.getHour());
            st.setInt(5, NotificationLog.getMinutes());
            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateNotificationLog(int userID, int NotificationID, NotificationLog updatedLog) {
        try (final Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("UPDATE NOTIFICATIONLOGS SET name=?, uid=?, eid=?, hour=?, minutes=? where uid=? AND nid=?");
            st.setString(1, updatedLog.getTitle());
            st.setInt(2, updatedLog.getUserID());
            st.setInt(3, updatedLog.getNotificationID());
            st.setInt(4, updatedLog.getHour());
            st.setInt(5, updatedLog.getMinutes());
            st.setString(6, Integer.toString(userID));
            st.setString(7, Integer.toString(NotificationID));
            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteNotificationLog(int userID, int NotificationID) {
        try (final Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("DELETE FROM NOTIFICATIONLOGS WHERE uid = ? AND eid = ?");
            st.setString(1, Integer.toString(userID));
            st.setString(2, Integer.toString(NotificationID));
            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }
}
