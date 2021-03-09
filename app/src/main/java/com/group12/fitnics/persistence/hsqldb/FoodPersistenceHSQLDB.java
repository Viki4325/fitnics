package com.group12.fitnics.persistence.hsqldb;

import android.util.Log;

import com.group12.fitnics.objects.Food;
import com.group12.fitnics.persistence.IFoodPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FoodPersistenceHSQLDB implements IFoodPersistence {

    private final String dbPath;

    public FoodPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Food fromResultSet(ResultSet rs) throws SQLException {
        int foodId = rs.getInt("foodId");
        String name = rs.getString("name");
        double caloriesPerGram = rs.getDouble("calPerGram");
        return new Food(foodId, name, caloriesPerGram);
    }

    @Override
    public ArrayList<Food> getFoodSequential() {
        return null;
    }

    @Override
    public Food getFoodByID(int foodID) {
        try (Connection c = connect()) {
            final PreparedStatement statement = c.prepareStatement("SELECT * FROM FOODS WHERE FOODS.id = ?");
            statement.setString(1, Integer.toString(foodID));
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
    public Food getFoodByFoodName(String foodName) {
        try (Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM FOODS WHERE FOODS.name = ?");
            st.setString(1, foodName);
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
    public void insertFood(Food currentFood) {
        try (final Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO FOODS VALUES(DEFAULT, ?, ?)");
            st.setString(1, currentFood.getName());
            st.setDouble(2, currentFood.getCalories());
            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    @Override
    public void updateFood(int foodID, Food currentFood) {
        try (final Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("UPDATE FOODS SET name=? calPerGram=? where id=?");
            st.setString(1, currentFood.getName());
            st.setDouble(2, currentFood.getCalories());
            st.setString(3, Integer.toString(foodID));
            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFood(int foodID) {
        try (final Connection c = connect()) {
            // Delete on cascade ???
            final PreparedStatement foodlogs = c.prepareStatement("DELETE FROM FOODLOGS WHERE fid = ?");
            foodlogs.setString(1, Integer.toString(foodID));
            foodlogs.executeUpdate();
            foodlogs.close();

            final PreparedStatement st = c.prepareStatement("DELETE FROM FOODS WHERE id = ?");
            st.setString(1, Integer.toString(foodID));
            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }
}
