package com.group12.fitnics.persistence.hsqldb;

import com.group12.fitnics.objects.Notification;
import com.group12.fitnics.persistence.INotificationPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationPersistenceHSQLDB implements INotificationPersistence {

    private final String dbPath;

    public NotificationPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Notification fromResultSet(ResultSet rs) throws SQLException {
        int NotificationID = rs.getInt("id");
        String name = rs.getString("name");
        int hour = rs.getInt("hour");
        int min = rs.getInt("min");
        boolean active = rs.getBoolean("active");
        Notification notification = new Notification(name, hour, min, active);
        notification.setNotificationID(NotificationID);
        return notification;
    }

    @Override
    public Notification getNotificationById(final int NotificationID){
        try (Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM NOTIFICATION WHERE id = ?");
            st.setString(1, Integer.toString(NotificationID));
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
    public void deleteNotification(final int NotificationID){
        try (final Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("DELETE FROM NOTIFICATION WHERE id = ?");
            st.setString(1, Integer.toString(NotificationID));
            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertNotification(final Notification notification)
    {
        try (final Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO NOTIFICATION VALUES(?, ?, ?, ?, ?)");
            st.setString(2, notification.getTitle());
            st.setInt(1,notification.getNotificationID());
            st.setInt(3,notification.getHour());
            st.setInt(4,notification.getMinute());
            st.setBoolean(5,notification.isActive());
            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }
}
