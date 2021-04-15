package com.group12.fitnics.persistence;

import com.group12.fitnics.objects.Notification;

public interface INotificationPersistence {

    Notification getNotificationById(final int NotificationID);
    void deleteNotification(final int NotificationID);
    void insertNotification(final Notification notification);
}
