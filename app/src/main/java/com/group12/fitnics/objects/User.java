package com.group12.fitnics.objects;
import com.group12.fitnics.objects.FoodLog;

import java.util.ArrayList;

public class User {
    private int userID;
    private String username;
    private String birthday;
    private int activityLevel;
    private double weight;
    private double height;
    private char gender;
    private int dailyCaloricNeeds;
    private static int lastUserID = -1;
    ArrayList<FoodLog> foodLogs;

    public User(String username, String birthday, int activityLevel, double weight, double height, char gender) {
        this.username = username;
        this.birthday = birthday;
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

    public String getBirthday() {
        return birthday;
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

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public void addFoodLog(FoodLog newLog){
        foodLogs.add(newLog);
    }

    public ArrayList<FoodLog> getFoodLogList(){
        return foodLogs;
    }
