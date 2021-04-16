package com.group12.fitnics.objects;

import java.io.Serializable;

public class NotificationLog implements Serializable {
    private String title;
    private int userID;
    private int notificationID;
    private int hour;
    private int minutes;

    public NotificationLog(String title, int userID, int notificationID, int hour, int minutes){
        this.title = title;
        this.notificationID = notificationID;
        this.userID = userID;
        this.minutes = minutes;
        this.hour = hour;
    }

    public int getHour() {
        return hour;
    }
    public int getMinutes() {
        return minutes;
    }
    public int getNotificationID() {
        return notificationID;
    }
    public int getUserID() {
        return userID;
    }
    public void setHour(int hour) {
        this.hour = hour;
    }
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
