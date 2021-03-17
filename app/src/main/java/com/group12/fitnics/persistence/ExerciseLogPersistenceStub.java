package com.group12.fitnics.persistence;

import com.group12.fitnics.exceptions.ExerciseLogNotFoundException;
import com.group12.fitnics.exceptions.InvalidExerciseLogException;
import com.group12.fitnics.objects.ExerciseLog;
import com.group12.fitnics.objects.MyDate;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class ExerciseLogPersistenceStub implements IExerciseLogPersistence {

    private List<ExerciseLog> exerciseLogs;

    public ExerciseLogPersistenceStub() {
        exerciseLogs = new ArrayList<>();

        MyDate date1 = new MyDate(new GregorianCalendar(2021, 0, 1));
        MyDate date2 = new MyDate(new GregorianCalendar(2021, 0, 2));

        exerciseLogs.add(new ExerciseLog(0, 2, date1, 10));
        exerciseLogs.add(new ExerciseLog(1, 0, date1, 5));
        exerciseLogs.add(new ExerciseLog(1, 1, date2, 10));
        exerciseLogs.add(new ExerciseLog(2, 2, date1, 5));
        exerciseLogs.add(new ExerciseLog(2, 5, date1, 10));
        exerciseLogs.add(new ExerciseLog(2, 6, date2, 10));
        exerciseLogs.add(new ExerciseLog(3, 3, date2, 10));
    }


    @Override
    public ExerciseLog getExerciseLog(int userID, int exerciseID, MyDate date) {
        boolean found = false;
        ExerciseLog result = null;
        for (int i = 0; i < exerciseLogs.size() && !found; i++) {
            ExerciseLog log = exerciseLogs.get(i);
            if (log.getUserID() == userID && log.getExerciseID() == exerciseID && log.getDate().equals(date)) {
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
    public List<ExerciseLog> getExerciseLogByUserDate(int userID, MyDate date) {
        List<ExerciseLog> list = new ArrayList<>();
        for (int i = 0; i < exerciseLogs.size(); i++) {
            ExerciseLog log = exerciseLogs.get(i);
            if (log.getUserID() == userID && log.getDate().equals(date))
                list.add(log);
        }
        return list;
    }

    @Override
    public void insertExerciseLog(ExerciseLog exerciseLog) throws InvalidExerciseLogException {
        if (!checkInvariant(exerciseLog))
            throw new InvalidExerciseLogException("The exercise log has invalid userID or exerciseID or minutes. ");

        // if there exists same exercise log already, to not allow it to be inserted
        if (getExerciseLog(exerciseLog.getUserID(), exerciseLog.getExerciseID(), exerciseLog.getDate()) != null)
            throw new InvalidExerciseLogException("The exercise log is duplicate. You could instead increase the time for this exercise from the logs. Thank you!");

        exerciseLogs.add(exerciseLog);
    }

    @Override
    public void updateExerciseLog(int userID, int exerciseID, MyDate date, ExerciseLog updatedLog) throws InvalidExerciseLogException, ExerciseLogNotFoundException {
        if (!checkInvariant(updatedLog))
            throw new InvalidExerciseLogException("The exercise log has invalid userID or exerciseID or minutes. ");

        boolean found = false;
        for (int i = 0; i < exerciseLogs.size() && !found; i++) {
            ExerciseLog log = exerciseLogs.get(i);
            if (log.getUserID() == userID && log.getExerciseID() == exerciseID && log.getDate().equals(date)) {
                exerciseLogs.set(i, updatedLog);
                found = true;
            }
        }
        if (!found)
            throw new ExerciseLogNotFoundException("There is no such exercise log to update. ");
    }

    @Override
    public void deleteExerciseLog(int userID, int exerciseID, MyDate date) throws ExerciseLogNotFoundException {
        boolean found = false;
        for (int i = 0; i < exerciseLogs.size() && !found; i++) {
            ExerciseLog log = exerciseLogs.get(i);
            if (log.getUserID() == userID && log.getExerciseID() == exerciseID && log.getDate().equals(date)) {
                exerciseLogs.remove(i);
                found = true;
            }
        }
        if (!found)
            throw new ExerciseLogNotFoundException("There is no such exercise log to delete. ");
    }

    private boolean checkInvariant(ExerciseLog exerciseLog) {
        if (exerciseLog == null || exerciseLog.getUserID() < 0 || exerciseLog.getExerciseID() < 0 || exerciseLog.getMinutes() <= 0)
            return false;
        return true;
    }
}
