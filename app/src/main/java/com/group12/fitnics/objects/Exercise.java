package com.group12.fitnics.objects;

public class Exercise {
    private String title;
    private String description;
    //ABS, CHEST, ARM,LEG
    private String category;
    private String level;
    private int caloriesBurn;

    public Exercise() {
    }

    public Exercise(String title, String description, String category, String level, int caloriesBurn) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.level = level;
        this.caloriesBurn = caloriesBurn;
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
    public String toString() {
        return "{" +
                "title=" + title +
                ", description=" + description +
                ", category=" + category +
                ", level=" + level +
                ", calories=" + caloriesBurn +
                '}';
    }
}
