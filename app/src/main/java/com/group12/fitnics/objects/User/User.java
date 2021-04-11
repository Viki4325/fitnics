package com.group12.fitnics.objects.User;

import com.group12.fitnics.business.DailyCaloricNeeds;
import com.group12.fitnics.exceptions.InvalidSignUpDateException;
import com.group12.fitnics.exceptions.InvalidUnitsException;
import com.group12.fitnics.exceptions.InvalidUserNameException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class User implements Serializable {
    public final static int WEIGHT_MIN = 30;//kg
    public final static int WEIGHT_MAX = 300;//kg
    public final static int HEIGHT_MIN = 50;//cm
    public final static int HEIGHT_MAX = 300;//cm

    private int userID;
    private String username;
    private int birthDay;
    private int birthMonth;
    private int birthYear;
    private IActLvl activityLevel; // (previously) 0 - Not Active, 1 - Somewhat Active, 2 - Active, 3 - Very Active
    private double weight;
    private double height;
    private IGender gender; // (previously) 'M' - Male, 'F' - Female, 'O' - Other
    private double dailyCaloricNeeds;
    private IGoal goal; // (previously) 0 - Lose Weight, 1 - Maintain Weight, 2 - Gain Weight
    // lastUserID is 3 because we are currently using a fake database for users.
    private static int lastUserID = -1;
    //units 0 - weight[0 - lbs, 1 - kg], 1 - height[0 - cm, 1 - ft]
    private int[] units = new int[2];

    public User(){}

    public User(String username, IActLvl activityLevel, double weight, double height, IGender gender) {
        this.username = username;
        this.activityLevel = activityLevel;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.dailyCaloricNeeds = DailyCaloricNeeds.resetDailyCaloricNeeds(this);
    }

    public User(String username, int bD, int bM, int bY, IActLvl actLvl, double w, double h, IGender gender, IGoal goal, int[] units) {
        this.username = username;
        this.birthDay = bD;
        this.birthMonth = bM;
        this.birthYear = bY;
        this.activityLevel = actLvl;
        this.weight = w;
        this.height = h;
        this.gender = gender;
        this.goal = goal;
        this.units = units;
        this.dailyCaloricNeeds = DailyCaloricNeeds.resetDailyCaloricNeeds(this);
    }

    public int getAge() {
        LocalDate birthday = LocalDate.of(birthYear, birthMonth, birthDay);
        LocalDate today = LocalDate.now();
        Period age = Period.between(birthday, today);
        return age.getYears();
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public double getDailyCaloricNeeds() {
        return dailyCaloricNeeds;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public IActLvl getActivityLevel() {
        return activityLevel;
    }

    public IGender getGender() {
        return gender;
    }

    public void setActivityLevel(IActLvl activityLevel) {
        this.activityLevel = activityLevel;
    }

    public void setDailyCaloricNeeds() {
        this.dailyCaloricNeeds = DailyCaloricNeeds.resetDailyCaloricNeeds(this);
    }

    public void setDailyCaloricNeeds(double caloryAmount) {
        dailyCaloricNeeds = (int) caloryAmount;
    }

    public void setGender(IGender gender) {
        this.gender = gender;
    }

    public void setHeight(double height)  throws InvalidUnitsException {
        if(!(height < WEIGHT_MIN) && !(height > WEIGHT_MAX) ){
            this.height = height;
        }else{
            throw new InvalidUnitsException("Invalid height units. Valid range is 50 - 300");
        }

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

    public void setUsername(String username) throws InvalidUserNameException {
        if( (username==null) || (username.equals("")) ) {
            throw new InvalidUserNameException("The username cannot be empty");
        }else if(username.length() > 20){
            throw new InvalidUserNameException("The username is too long, should be no more than 20 characters.");
        } else{
            this.username = username;
        }
    }

    public void setWeight(double weight) throws InvalidUnitsException {
        if(!(weight < WEIGHT_MIN) && !(weight > WEIGHT_MAX) ){
            this.weight = weight;
        }else{
            throw new InvalidUnitsException("Invalid weight units. Valid range is 30 - 300");
        }
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

    public IGoal getGoal() {
        return goal;
    }

    public void setBirthDay(int birthDay) throws InvalidSignUpDateException {
        if(birthDay > 0) {
            this.birthDay = birthDay;
        }else{
            throw new InvalidSignUpDateException("Invalid date choice");
        }
    }

    public void setBirthMonth(int birthMonth) throws InvalidSignUpDateException {
        if(birthMonth > 0) {
            this.birthMonth = birthMonth;
        }else{
            throw new InvalidSignUpDateException("Invalid date choice");
        }

    }

    public void setBirthYear(int birthYear) throws InvalidSignUpDateException {
        if(birthYear > 0){
            this.birthYear = birthYear;
        }else{
            throw new InvalidSignUpDateException("Invalid date choice");
        }
    }

    public void setGoal(IGoal goal) {
        this.goal = goal;
    }

    public void setUnits(int[] units) {
        this.units = units;
    }

    public int[] getUnits() {
        return units;
    }
}
