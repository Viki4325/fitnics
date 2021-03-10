package com.group12.fitnics.persistence.hsqldb;

import android.util.Log;

import com.group12.fitnics.objects.Exercise;
import com.group12.fitnics.persistence.IExercisePersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExercisePersistenceHSQLDB implements IExercisePersistence {

    private final String dbPath;

    public ExercisePersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Exercise fromResultSet(ResultSet rs) throws SQLException {
        int exerciseID = rs.getInt("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        String category = rs.getString("category");
        String level = rs.getString("level");
        int caloriesPerMin = rs.getInt("calPerMin");
        Exercise exercise = new Exercise(name, description, category, level, caloriesPerMin);
        exercise.setExerciseID(exerciseID);
        return exercise;
    }

    @Override
    public List<Exercise> getExerciseList() {
        final List<Exercise> exercises = new ArrayList<>();
        try (final Connection c = connect()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM EXERCISES");
            while (rs.next()) {
                final Exercise exercise = fromResultSet(rs);
                exercises.add(exercise);
            }
            rs.close();
            st.close();

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return exercises;
    }

    @Override
    public Exercise getExerciseById(int exerciseID) {
        try (Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM EXERCISES WHERE id = ?");
            st.setString(1, Integer.toString(exerciseID));
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
    public Exercise getExerciseByName(String exerciseName) {
        try (Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM EXERCISES WHERE name = ?");
            st.setString(1, exerciseName);
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
    public List<Exercise> getExerciseByLevel(String exerciseLevel) {
        List<Exercise> exercises = new ArrayList<>();
        try (Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM EXERCISES WHERE level = ?");
            st.setString(1, exerciseLevel);
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final Exercise exercise = fromResultSet(rs);
                exercises.add(exercise);
            }
            rs.close();
            st.close();

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }

        return exercises;
    }

    @Override
    public List<Exercise> getExerciseByCategory(String exerciseCategory) {
        List<Exercise> exercises = new ArrayList<>();
        try (Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM EXERCISES WHERE category = ?");
            st.setString(1, exerciseCategory);
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final Exercise exercise = fromResultSet(rs);
                exercises.add(exercise);
            }
            rs.close();
            st.close();

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }

        return exercises;
    }

    @Override
    public void insertExercise(Exercise newExercise) {
        try (final Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO EXERCISES VALUES(DEFAULT, ?, ?, ?, ?, ?)");
            st.setString(1, newExercise.getTitle());
            st.setString(2, newExercise.getDescription());
            st.setString(3, newExercise.getCategory());
            st.setString(4, newExercise.getLevel());
            st.setInt(5, newExercise.getCaloriesBurn());
            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    @Override
    public void updateExercise(int exerciseID, Exercise currentExercise) {
        try (final Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement(
                    "UPDATE EXERCISES SET name=? description=? category=? level=? calPerMin=? where id = ?");
            st.setString(1, currentExercise.getTitle());
            st.setString(2, currentExercise.getDescription());
            st.setString(3, currentExercise.getCategory());
            st.setString(4, currentExercise.getLevel());
            st.setInt(5, currentExercise.getCaloriesBurn());
            st.setString(6, Integer.toString(exerciseID));
            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteExercise(int exerciseID) {
        try (final Connection c = connect()) {
            // Delete on cascade
            final PreparedStatement eLogsSt = c.prepareStatement("DELETE FROM EXERCISELOGS WHERE eid = ?");
            eLogsSt.setString(1, Integer.toString(exerciseID));
            eLogsSt.executeUpdate();
            eLogsSt.close();

            final PreparedStatement st = c.prepareStatement("DELETE FROM EXERCISES WHERE id = ?");
            st.setString(1, Integer.toString(exerciseID));
            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteExercise(Exercise currentExercise) {
        int exerciseID = currentExercise.getExerciseID();
        deleteExercise(exerciseID);
    }
}
