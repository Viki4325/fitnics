package com.group12.fitnics.objects;

public class User {
    private int userID;
    private String username;
    private int birthDay;
    private int birthMonth;
    private int birthYear;
    // activityLevel: 0 - Not Active, 1 - Somewhat Active, 2 - Active, 3 - Very Active
    private int activityLevel;
    private double weight;
    private double height;
    // gender: 'M' - Male, 'F' - Female, 'O' - Other
    private char gender;
    private int dailyCaloricNeeds;
    // goal: 0 - Loose Weight, 1 - Maintain Weight, 2 - Gain Weight
    private int goal;
    // lastUserID is 3 because we are currently using a fake database for users.
    private static int lastUserID = -1;
    //units 0 - weight[0 - lbs, 1 - kg], 1 - height[0 - cm, 1 - ft]
    private int[] units = new int[2];

    public User(){}

    public User(String username, int activityLevel, double weight, double height, char gender) {
        this.username = username;
        this.activityLevel = activityLevel;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.dailyCaloricNeeds = 0;
    }

    public User(String username, int bD, int bM, int bY, int actLvl, double w, double h, char gender, int goal, int[] units) {
        this.username = username;
        this.birthDay = bD;
        this.birthMonth = bM;
        this.birthYear = bY;
        this.activityLevel = actLvl;
        this.weight = w;
        this.height = h;
        this.gender = gender;
        this.dailyCaloricNeeds = 0;
        this.goal = goal;
        this.units = units;
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

    public static void setLastUserID(int i) {
        lastUserID = i;
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

    public void setUnits(int[] units) {
        this.units = units;
    }

    public int[] getUnits() {
        return units;
    }
}
