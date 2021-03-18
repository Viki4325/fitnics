package com.group12.fitnics.objects;

import java.io.Serializable;
import com.group12.fitnics.business.DateHelper;

import java.time.LocalDate;

public class ExerciseLog implements Serializable {
    private int userID;
    private int exerciseID;
    private LocalDate date;
    private int minutes;

    public ExerciseLog(int userId, int exerciseID, LocalDate date, int minutes) {
        this.userID = userId;
        this.exerciseID = exerciseID;
        this.date = date;
        this.minutes = minutes;
    }

    public ExerciseLog(int userId, int exerciseID, String dateStr, int minutes) {
        this.userID = userId;
        this.exerciseID = exerciseID;
        this.date = DateHelper.stringToDate(dateStr);
        this.minutes = minutes;
    }

    public int getUserID() {
        return userID;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDateString() {
        return DateHelper.dateToString(date);
    }

    public int getExerciseID() {
        return exerciseID;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setUserID(int id) { this.userID = id; }

    public void setExerciseIDID(int id) { this.exerciseID = id; }

    public void setDate(LocalDate date) { this.date = date; }

    public void setDateString(String dateStr) {
        this.date = DateHelper.stringToDate(dateStr);
    }

    public void setMinutes(int minutes) { this.minutes = minutes; }

}
