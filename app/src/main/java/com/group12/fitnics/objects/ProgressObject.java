package com.group12.fitnics.objects;

import java.time.LocalDate;

public class ProgressObject {

    private LocalDate date;
    private int caloriesConsumed;
    private int minutesWorkedOut;
    private int dailyCaloricNeeds;

    public ProgressObject(LocalDate date, int caloriesConsumed, int minutesWorkedOut, int dailyCaloricNeeds) {
        this.date = date;
        this.caloriesConsumed = caloriesConsumed;
        this.minutesWorkedOut = minutesWorkedOut;
        this.dailyCaloricNeeds = dailyCaloricNeeds;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getCaloriesConsumed() {
        return caloriesConsumed;
    }

    public int getMinutesWorkedOut() {
        return minutesWorkedOut;
    }

    public int getDailyCaloricNeeds() {
        return dailyCaloricNeeds;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCaloriesConsumed(int caloriesConsumed) {
        this.caloriesConsumed = caloriesConsumed;
    }

    public void setMinutesWorkedOut(int minutesWorkedOut) {
        this.minutesWorkedOut = minutesWorkedOut;
    }

    public void setDailyCaloricNeeds(int dailyCaloricNeeds) {
        this.dailyCaloricNeeds = dailyCaloricNeeds;
    }
}
