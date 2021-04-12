package com.group12.fitnics.objects;

import java.io.Serializable;

public class Notification implements Serializable {
    private String title;
    private int notificationID;
    private int hour;
    private int minute;
    private boolean active;
    private static int lastNotificationID = -1;

    Notification(){}

    public Notification(String title, int hour, int minute, boolean active)
    {
        this.title = title;
        this.minute = minute;
        this.active = active;
        this.hour = hour;
    }


    /*
     *   set the exerciseID only when inserting an exercise
     * */
    public void setNotificationID() {
        lastNotificationID++;
        this.notificationID = lastNotificationID;
    }

    public void setNotificationID(int id){
        this.notificationID = id;
        if(id > lastNotificationID)
            lastNotificationID = this.notificationID;
    }

    public int getHour() {
        return hour;
    }
    public int getMinute() {
        return minute;
    }
    public void setHour(int hour){this.hour = hour;}
    public void setMinute(int minute){this.minute = minute;}
    public boolean isActive() {
        return active;
    }
    public String getTitle(){return title;}
    public void setIsActive(Boolean active){this.active = active;}
    public int getNotificationID(){return this.notificationID;}

    public void setTitle(String title) {this.title = title;}
}
