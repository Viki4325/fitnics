package com.group12.fitnics.persistence.hsqldb;

import com.group12.fitnics.exceptions.HSQLDBException;
import com.group12.fitnics.objects.User;
import com.group12.fitnics.persistence.IUserPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        int userID = rs.getInt("id");
        String username = rs.getString("username");
        int birthDay = rs.getInt("birthDay");
        int birthMonth = rs.getInt("birthMonth");
        int birthYear = rs.getInt("birthYear");
        int activityLvl = Integer.parseInt(rs.getString("actLvl"));
        double weight = Double.parseDouble(rs.getString("weight"));
        double height = Double.parseDouble(rs.getString("height"));
        char gender = rs.getString("gender").charAt(0);
        Integer dailyCaloricNeeds = rs.getInt("caloricNeeds");
        Integer goal = rs.getInt("goal");
        int[] units = {0, 0};
        if (rs.getString("wUnit").equals("kg"))
            units[0] = 1;
        if (rs.getString("hUnit").equals("ft"))
            units[1] = 1;

        User user = new User(username, birthDay, birthMonth, birthYear, activityLvl, weight, height, gender, goal, units);
        user.setUserID(userID);
        return user;
    }

    @Override
    public List<User> getUserSequential() {
        final List<User> users = new ArrayList<>();
        try (final Connection c = connect()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM USERS");
            while (rs.next()) {
                final User user = fromResultSet(rs);
                users.add(user);
            }
            rs.close();
            st.close();

        } catch (final SQLException e) {
            throw new HSQLDBException(e);
        }
        return users;
    }

    @Override
    public User getUserByID(int userID) {
        try (Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM USERS WHERE USERS.id = ?");
            st.setString(1, Integer.toString(userID));
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
    public User getUserByUsername(String username) {
        try (Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM USERS WHERE USERS.username = ?");
            st.setString(1, username);
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
    public int insertUser(User currentUser) {

        try (final Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO USERS VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            st.setString(1, currentUser.getUsername());
            st.setString(2, Integer.toString(currentUser.getBirthDay()));
            st.setString(3, Integer.toString(currentUser.getBirthMonth()));
            st.setString(4, Integer.toString(currentUser.getBirthYear()));
            st.setInt(5, currentUser.getActivityLevel().getValue());
            st.setDouble(6, currentUser.getWeight());
            st.setDouble(7, currentUser.getHeight());
            st.setString(8, String.valueOf(currentUser.getGender().getValue()));
            st.setDouble(9, currentUser.getDailyCaloricNeeds());
            st.setInt(10, currentUser.getGoal().getValue());
            String wUnit = "lbs";
            String hUnit = "cm";
            if (currentUser.getUnits()[0] == 1)
                wUnit = "kg";
            if (currentUser.getUnits()[1] == 1)
                hUnit = "ft";
            st.setString(11, wUnit);
            st.setString(12, hUnit);

            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            throw new HSQLDBException(e);
        }

        User created = getUserByUsername(currentUser.getUsername());
        return created.getUserID();
    }

    @Override
    public void updateUser(int userID, User currentUser) {

        try (final Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("UPDATE USERS SET username=?, actLvl=?, weight=?, height=?, gender=?, caloricNeeds=?, goal=? WHERE id = ?");
            st.setString(1, currentUser.getUsername());
            st.setInt(2, currentUser.getActivityLevel().getValue());
            st.setDouble(3, currentUser.getWeight());
            st.setDouble(4, currentUser.getHeight());
            st.setString(5, String.valueOf(currentUser.getGender().getValue()));
            st.setDouble(6, currentUser.getDailyCaloricNeeds());
            st.setInt(7, currentUser.getGoal().getValue());
            st.setString(8, Integer.toString(userID));
            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            throw new HSQLDBException(e);
        }
    }

    @Override
    public void deleteUser(int userID) {

        try (final Connection c = connect()) {
            // Delete on cascade
            final PreparedStatement foodlogs = c.prepareStatement("DELETE FROM FOODLOGS WHERE uid = ?");
            foodlogs.setString(1, Integer.toString(userID));
            foodlogs.executeUpdate();
            foodlogs.close();

            // Delete on cascade
            final PreparedStatement excslogs = c.prepareStatement("DELETE FROM EXERCISELOGS WHERE uid = ?");
            excslogs.setString(1, Integer.toString(userID));
            excslogs.executeUpdate();
            excslogs.close();

            final PreparedStatement st = c.prepareStatement("DELETE FROM USERS WHERE id = ?");
            st.setString(1, Integer.toString(userID));
            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            throw new HSQLDBException(e);
        }
    }
}
