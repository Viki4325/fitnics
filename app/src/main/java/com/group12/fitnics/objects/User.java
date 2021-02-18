package com.group12.fitnics.objects;

import java.util.ArrayList;

public class User {
    private int uID;
    private String username;
    private String password;
    private String birthday;
    private int activityLevel;
    private Double weight;
    private Double height;
    private int gender;
    private int dailyCaloricNeeds;
    private ArrayList<DayLog> logs;

    public Double getHeight() {
        return height;
    }

    public ArrayList<DayLog> getLogs() {
        return logs;
    }

    public Double getWeight() {
        return weight;
    }

    public int getDailyCaloricNeeds() {
        return dailyCaloricNeeds;
    }

    public int getuID() {
        return uID;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public int getActivityLevel() {
        return activityLevel;
    }

    public int getGender() {
        return gender;
    }

    public void setActivityLevel(int activityLevel) {
        this.activityLevel = activityLevel;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setDailyCaloricNeeds(int dailyCaloricNeeds) {
        this.dailyCaloricNeeds = dailyCaloricNeeds;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setLogs(ArrayList<DayLog> logs) {
        this.logs = logs;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setupID(int uID) {
        this.uID = uID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
