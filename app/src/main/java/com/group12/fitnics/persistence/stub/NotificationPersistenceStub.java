package com.group12.fitnics.persistence.stub;


import com.group12.fitnics.objects.Notification;
import com.group12.fitnics.persistence.INotificationPersistence;

import java.util.ArrayList;
import java.util.List;

public class NotificationPersistenceStub implements INotificationPersistence {

    private List<Notification> NotificationList;

    public NotificationPersistenceStub(){
        this.NotificationList = new ArrayList<>();

        Notification workout1 = new Notification("Workout", 12, 10, false);
        Notification breakfast = new Notification("Breakfast", 7, 30, true);
        Notification lunch = new Notification("Lunch", 13, 0, true);
        Notification dinner = new Notification("Dinner", 18, 0, true);
        Notification workout2 = new Notification("Workout", 8, 0, true);
        Notification snack = new Notification("Snack", 16, 30, false);
        Notification sleep = new Notification("Sleep", 22, 55, true);

        workout1.setNotificationID(0);
        breakfast.setNotificationID(1);
        lunch.setNotificationID(2);
        dinner.setNotificationID(3);
        workout2.setNotificationID(4);
        snack.setNotificationID(5);
        sleep.setNotificationID(6);

        NotificationList.add(workout1);
        NotificationList.add(breakfast);
        NotificationList.add(lunch);
        NotificationList.add(dinner);
        NotificationList.add(workout2);
        NotificationList.add(snack);
        NotificationList.add(sleep);


    }

    @Override
    public Notification getNotificationById(int NotificationID){
        for (int i = 0; i < NotificationList.size() ; i++) {
            if(NotificationList.get(i).getNotificationID() == NotificationID){
                return NotificationList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteNotification(int NotificationID){
        boolean found = false;
        for (int i = 0; i < NotificationList.size() && !found; i++) {
            if(NotificationList.get(i).getNotificationID() == NotificationID){
                NotificationList.remove(i);
                found = true;
            }
        }
    }

    public void insertNotification(Notification notification) {
        NotificationList.add(notification);
    }
}
