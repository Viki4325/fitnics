package com.group12.fitnics.objects;

import java.util.ArrayList;

public class DayLog {
    String date;
    ArrayList<Food> foods;
    ArrayList<Exercise> exercises;

    public DayLog(String d, ArrayList<Food> f, ArrayList<Exercise> e) {
        date = d;
        foods = f;
        exercises = e;
    }

    public String getDate() {
        return date;
    }
    public ArrayList<Food> getFoods() {
        return foods;
    }
    public ArrayList<Exercise> getExercise() {
        return exercises;
    }
    public void setFoods(ArrayList<Food> inFoods) {
        foods = inFoods;
    }
    public void setExercise(ArrayList<Exercise> inExercises) {
        exercises = inExercises;
    }
}
