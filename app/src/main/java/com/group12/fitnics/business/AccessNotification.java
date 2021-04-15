package com.group12.fitnics.business;

import com.group12.fitnics.application.Services;
import com.group12.fitnics.exceptions.InvalidExerciseException;
import com.group12.fitnics.exceptions.InvalidNotificationException;
import com.group12.fitnics.exceptions.InvalidNotificationLogException;
import com.group12.fitnics.exceptions.NotificationNotFoundException;
import com.group12.fitnics.objects.Notification;
import com.group12.fitnics.persistence.INotificationPersistence;

public class AccessNotification {
    private final INotificationPersistence notificationPersistence;

    public AccessNotification(){
        notificationPersistence = Services.getNotificationPersistence();
    }

    public AccessNotification(INotificationPersistence notificationPersistence){
        this.notificationPersistence = notificationPersistence;
    }

    public Notification getNotificationById(int notificationId){
        if(notificationId >= 0){
            return notificationPersistence.getNotificationById(notificationId);
        }
        return null;
    }

    public void deleteNotificationById(int notificationId) throws InvalidNotificationException, NotificationNotFoundException {
        if(notificationId < 0)
            throw new InvalidNotificationException("The Notification id is not valid. ");

        Notification result = notificationPersistence.getNotificationById(notificationId);
        if(result == null)
            throw new NotificationNotFoundException("There's no Notification with the id. ");

        notificationPersistence.deleteNotification(notificationId);
    }

    public void insertNotification(Notification notify) throws InvalidNotificationException
    {
        if(notify == null)
            throw new InvalidNotificationException("The Notification is not valid. ");
        if (getNotificationById(notify.getNotificationID()) != null)
            throw new InvalidNotificationLogException("The Notification is duplicate. You could instead increase the time for this Notification. Thank you!");

        notificationPersistence.insertNotification(notify);
        notify.setNotificationID(notify.getNotificationID());
    }
}
