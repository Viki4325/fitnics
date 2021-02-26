package com.group12.fitnics.objects;
import com.group12.fitnics.objects.FoodLog;

import java.util.ArrayList;

public class User {
    private int userID;
    private String username;
    private int birthDay;
    private int birthMonth;
    private int birthYear;
    private int activityLevel;
    private double weight;
    private double height;
    private char gender;
    private int dailyCaloricNeeds;
    private int goal;
    private static int lastUserID = -1;
    ArrayList<FoodLog> foodLogs;

    public User(){}

    public User(String username, int activityLevel, double weight, double height, char gender) {
        this.username = username;
        this.activityLevel = activityLevel;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int getDailyCaloricNeeds() {
        return dailyCaloricNeeds;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public int getActivityLevel() {
        return activityLevel;
    }

    public char getGender() {
        return gender;
    }

    public void setActivityLevel(int activityLevel) {
        this.activityLevel = activityLevel;
    }

    public void setDailyCaloricNeeds(int dailyCaloricNeeds) {
        this.dailyCaloricNeeds = dailyCaloricNeeds;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    // set the userID only when insert a user
    public void setUserID() {
        lastUserID++;
        this.userID = lastUserID;
    }

    public void setUserID(int id) {
        this.userID = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public int getGoal() {
        return goal;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }
}
