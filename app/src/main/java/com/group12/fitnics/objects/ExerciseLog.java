package com.group12.fitnics.objects;

import java.util.Calendar;

public class ExerciseLog {
    private int userID;
    private int exerciseID;
    private MyDate date;
    private int minutes;

    public ExerciseLog(int userId, int foodId, MyDate date, int minutes) {
        this.userID = userId;
        this.exerciseID = foodId;
        this.date = date;
        this.minutes = minutes;
    }

    public int getUserID() {
        return userID;
    }
    public MyDate getDate() {
        return date;
    }
    public int getExerciseID() {
        return exerciseID;
    }
    public int getMinutes() {
        return minutes;
    }

    public void setUserID(int id) { this.userID = id; }
    public void setExerciseIDID(int id) { this.exerciseID = id; }
    public void setDate(MyDate date) { this.date = date; }
    public void setMinutes(int minutes) { this.minutes = minutes; }

}
