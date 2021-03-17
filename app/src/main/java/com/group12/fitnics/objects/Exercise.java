package com.group12.fitnics.objects;

import android.widget.SearchView;

import java.io.Serializable;

public class Exercise implements Serializable {
    private int exerciseID;
    private String title;
    private String description;
    private String category;
    private String level; 
    private int caloriesBurn;
    private static int lastExerciseID = -1;

    public Exercise() { }

    /*
     * This constructor allows you to set all instance variable EXCEPT exerciseID
     * */
    public Exercise(String title, String description, String category, String level, int caloriesBurn) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.level = level;
        this.caloriesBurn = caloriesBurn;
    }

    public int getExerciseID() { return exerciseID; }

    public void setExerciseID(int id) {
        this.exerciseID = id;
        //sync the lastUserId
        lastExerciseID = this.exerciseID;
    }

    /*
     *   set the exerciseID only when inserting an exercise
     * */
    public void setExerciseID() {
        lastExerciseID++;
        this.exerciseID = lastExerciseID;
    }

    /*
     *Update ID when item is deleted
     *  */
    public void updateId(){
        lastExerciseID--;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLevel()
    {
        return this.level;
    }

    public void setLevel(String level)
    {
        this.level = level;
    }

    public int getCaloriesBurn()
    {
        return this.caloriesBurn;
    }


    public void setCaloriesBurn(int caloriesBurn)
    {
        this.caloriesBurn = caloriesBurn;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Exercise){
            Exercise currExercise = (Exercise)o;
            return this.getTitle().equalsIgnoreCase( currExercise.getTitle());
        }
        return false;
    }

}
