package com.group12.fitnics.persistence.hsqldb;

import android.util.Log;

import com.group12.fitnics.objects.User;
import com.group12.fitnics.persistence.IUserPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserPersistenceHSQLDB implements IUserPersistence {

    private final String dbPath;

    public UserPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private User fromResultSet(final ResultSet rs) throws SQLException {
        String userID = rs.getString("id");
        String username = rs.getString("username");
        String birthDay = rs.getString("birthDay");
        String birthMonth = rs.getString("birthMonth");
        String birthYear = rs.getString("birthYear");
        int activityLvl = Integer.parseInt(rs.getString("actLvl"));
        double weight = Double.parseDouble(rs.getString("weight"));
        double height = Double.parseDouble(rs.getString("height"));
        char gender = rs.getString("gender").charAt(0);
        String dailyCaloricNeeds = rs.getString("caloricNeeds");
        String goal = rs.getString("goal");
//        String unit = rs.getString("unit"); // ?????

        return new User(username, activityLvl, weight, height, gender);
    }

    @Override
    public List<User> getUserSequential() {
        // TO DO

        return null;
    }

    @Override
    public User getUserByID(int userID) {
        try (Connection c = connect()) {
            final PreparedStatement statement = c.prepareStatement("SELECT * FROM USERS WHERE USERS.id = ?");
            statement.setString(1, Integer.toString(userID));
            final ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return fromResultSet(rs);
            }

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        try (Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM USERS WHERE USERS.username = ?");
            st.setString(1, username);
            final ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return fromResultSet(rs);
            }

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insertUser(User currentUser) {
        try (final Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO USERS VALUES(DEFAULT, ?, ?, ?, ?, ?)");
            st.setString(1, currentUser.getUsername());
            st.setInt(2, currentUser.getActivityLevel());
            st.setDouble(3, currentUser.getWeight());
            st.setDouble(4, currentUser.getHeight());
            st.setString(5, String.valueOf(currentUser.getGender()));
            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(int userID, User currentUser) {
        try (final Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("UPDATE USERS SET username=?, activityLvl=? weight=? height=? gender=? caloricNeeds=? goal=? WHERE id = ?");
            st.setString(1, currentUser.getUsername());
            st.setInt(2, currentUser.getActivityLevel());
            st.setDouble(3, currentUser.getWeight());
            st.setDouble(4, currentUser.getHeight());
            st.setString(5, String.valueOf(currentUser.getGender()));
            st.setInt(6, currentUser.getDailyCaloricNeeds());
            st.setInt(7, currentUser.getGoal());
            st.setString(8, Integer.toString(userID));
            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int userID) {
        try (final Connection c = connect()) {
            // Delete on cascade ???
            final PreparedStatement foodlogs = c.prepareStatement("DELETE FROM FOODLOGS WHERE uid = ?");
            foodlogs.setString(1, Integer.toString(userID));
            foodlogs.executeUpdate();
            foodlogs.close();

            // Delete on cascade ???
            final PreparedStatement excslogs = c.prepareStatement("DELETE FROM EXERCISELOGS WHERE uid = ?");
            excslogs.setString(1, Integer.toString(userID));
            excslogs.executeUpdate();
            excslogs.close();

            final PreparedStatement st = c.prepareStatement("DELETE FROM USERS WHERE id = ?");
            st.setString(1, Integer.toString(userID));
            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }
}
