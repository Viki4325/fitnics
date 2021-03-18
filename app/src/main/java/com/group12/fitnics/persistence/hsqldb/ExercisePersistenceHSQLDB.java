package com.group12.fitnics.persistence.hsqldb;

import com.group12.fitnics.exceptions.ExerciseNotFoundException;
import com.group12.fitnics.exceptions.InvalidExNameException;
import com.group12.fitnics.exceptions.InvalidExerciseException;
import com.group12.fitnics.objects.Exercise;
import com.group12.fitnics.persistence.IExercisePersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        final List<Exercise> exerciseList = new ArrayList<>();
        try (final Connection c = connect()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM EXERCISES");
            while (rs.next()) {
                final Exercise exercise = fromResultSet(rs);
                exerciseList.add(exercise);
            }
            rs.close();
            st.close();

        } catch (final SQLException e) {
            e.printStackTrace();
        }

        List<Exercise> exerciseSortedAlpha = new ArrayList<>(exerciseList.size());

        exerciseSortedAlpha.addAll(exerciseList);

        Collections.sort(exerciseSortedAlpha, new Comparator<Exercise>() {
            @Override
            public int compare(Exercise exercise1, Exercise exercise2) {
                return (exercise1.getTitle()).compareToIgnoreCase(exercise2.getTitle());
            }
        });

        return exerciseSortedAlpha;
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
            rs.close();
            st.close();

        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Exercise getExerciseByName(String exerciseName) {
        try (Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM EXERCISES WHERE LOWER(name) = LOWER(?)");
            st.setString(1, exerciseName);
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
            e.printStackTrace();
        }

        return exercises;
    }

    @Override
    public int insertExercise(Exercise newExercise) throws InvalidExerciseException {
        if(newExercise == null)
            throw new InvalidExerciseException("The exercise is not valid. ");
        if(newExercise.getTitle().length() > 20)
            throw new InvalidExNameException("The name should be no more than 20 characters.");
        if(newExercise.getDescription().length() > 1024)
            throw new InvalidExNameException("The description should be no more than 1024 characters.");
        if(newExercise.getLevel().length() > 20)
            throw new InvalidExNameException("The level should be no more than 20 characters.");
        if(newExercise.getCategory().length() > 20)
            throw new InvalidExNameException("The category should be no more than 20 characters.");

        try (final Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO EXERCISES VALUES(?, ?, ?, ?, ?, ?)");

            newExercise.setExerciseID();

            st.setString(1, Integer.toString(newExercise.getExerciseID()));
            st.setString(2, newExercise.getTitle());
            st.setString(3, newExercise.getDescription());
            st.setString(4, newExercise.getCategory());
            st.setString(5, newExercise.getLevel());
            st.setInt(6, newExercise.getCaloriesBurn());
            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            e.printStackTrace();
        }

        return newExercise.getExerciseID();
    }

    @Override
    public void updateExercise(int exerciseID, Exercise currentExercise) throws InvalidExerciseException, ExerciseNotFoundException {
        if(currentExercise == null)
            throw new InvalidExerciseException("The exercise is not valid. ");

        if (getExerciseById(exerciseID) == null)
            throw new ExerciseNotFoundException("There's no exercise with the exerciseID. ");

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
            e.printStackTrace();
        }
    }

    @Override
    public void deleteExercise(int exerciseID) throws ExerciseNotFoundException {
        if (getExerciseById(exerciseID) == null)
            throw new ExerciseNotFoundException("There's no exercise with the exerciseID. ");

        try (final Connection c = connect()) {
            final PreparedStatement st = c.prepareStatement("DELETE FROM EXERCISES WHERE id = ?");
            st.setString(1, Integer.toString(exerciseID));
            st.executeUpdate();
            st.close();

        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteExercise(Exercise currentExercise) throws ExerciseNotFoundException {
        if (getExerciseById(currentExercise.getExerciseID()) == null)
            throw new ExerciseNotFoundException("There's no exercise to delete. ");

        deleteExercise(currentExercise.getExerciseID());
    }
}
