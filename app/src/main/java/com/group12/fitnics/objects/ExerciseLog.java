package com.group12.fitnics.objects;

public class ExerciseLog {
    private int userID;
    private int exerciseID;
    private String date;
    private int minutes;

    public ExerciseLog(int userId, int foodId, String date, int minutes) {
        this.userID = userId;
        this.exerciseID = foodId;
        this.date = date;
        this.minutes = minutes;
    }

    public int getUserID() {
        return userID;
    }
    public String getDate() {
        return date;
    }
    public int getExerciseID() {
        return exerciseID;
    }
    public int getGrams() {
        return minutes;
    }

    public void setUserID(int id) { this.userID = id; }
    public void setExerciseIDID(int id) { this.exerciseID = id; }
    public void setDate(String date) { this.date = date; }
    public void setMinutes(int minutes) { this.minutes = minutes; }

}
