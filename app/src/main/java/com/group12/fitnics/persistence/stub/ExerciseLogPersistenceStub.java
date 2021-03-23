package com.group12.fitnics.persistence.stub;

import com.group12.fitnics.objects.ExerciseLog;
import com.group12.fitnics.persistence.IExerciseLogPersistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExerciseLogPersistenceStub implements IExerciseLogPersistence {

    private List<ExerciseLog> exerciseLogs;

    public ExerciseLogPersistenceStub() {
        exerciseLogs = new ArrayList<>();

//        MyDate date1 = new MyDate(new GregorianCalendar(2021, 0, 1));
//        MyDate date2 = new MyDate(new GregorianCalendar(2021, 0, 2));
        LocalDate date1 = LocalDate.of(2021, 1, 1);
        LocalDate date2 = LocalDate.of(2021, 1, 2);

        exerciseLogs.add(new ExerciseLog(0, 2, date1, 10));
        exerciseLogs.add(new ExerciseLog(1, 0, date1, 5));
        exerciseLogs.add(new ExerciseLog(1, 1, date2, 10));
        exerciseLogs.add(new ExerciseLog(2, 2, date1, 5));
        exerciseLogs.add(new ExerciseLog(2, 5, date1, 10));
        exerciseLogs.add(new ExerciseLog(2, 6, date2, 10));
        exerciseLogs.add(new ExerciseLog(3, 3, date2, 10));
    }


    @Override
    public ExerciseLog getExerciseLog(int userID, int exerciseID, LocalDate date) {
        boolean found = false;
        ExerciseLog result = null;
        for (int i = 0; i < exerciseLogs.size() && !found; i++) {
            ExerciseLog log = exerciseLogs.get(i);
            if (log.getUserID() == userID && log.getExerciseID() == exerciseID && log.getDate().isEqual(date)) {
                found = true;
                result = log;
            }
        }
        return result;
    }

    @Override
    public List<ExerciseLog> getExerciseLogByUser(int userID) {
        List<ExerciseLog> list = new ArrayList<>();
        for (int i = 0; i < exerciseLogs.size(); i++) {
            if (exerciseLogs.get(i).getUserID() == userID)
                list.add(exerciseLogs.get(i));
        }
        return list;
    }

    @Override
    public List<ExerciseLog> getExerciseLogByUserDate(int userID, LocalDate date) {
        List<ExerciseLog> list = new ArrayList<>();
        for (int i = 0; i < exerciseLogs.size(); i++) {
            ExerciseLog log = exerciseLogs.get(i);
            if (log.getUserID() == userID && log.getDate().isEqual(date))
                list.add(log);
        }
        return list;
    }

    @Override
    public void insertExerciseLog(ExerciseLog exerciseLog) {

        exerciseLogs.add(exerciseLog);
    }

    @Override
    public void updateExerciseLog(int userID, int exerciseID, LocalDate date, ExerciseLog updatedLog) {
        boolean found = false;
        for (int i = 0; i < exerciseLogs.size() && !found; i++) {
            ExerciseLog log = exerciseLogs.get(i);
            if (log.getUserID() == userID && log.getExerciseID() == exerciseID && log.getDate().isEqual(date)) {
                exerciseLogs.set(i, updatedLog);
                found = true;
            }
        }
    }

    @Override
    public void deleteExerciseLog(int userID, int exerciseID, LocalDate date) {
        boolean found = false;
        for (int i = 0; i < exerciseLogs.size() && !found; i++) {
            ExerciseLog log = exerciseLogs.get(i);
            if (log.getUserID() == userID && log.getExerciseID() == exerciseID && log.getDate().isEqual(date)) {
                exerciseLogs.remove(i);
                found = true;
            }
        }
    }

}
