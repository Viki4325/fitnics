package com.group12.fitnics.persistence;

import com.group12.fitnics.objects.NotificationLog;

import java.util.List;

public interface INotificationLogPersistence {

    NotificationLog getNotificationLog(final int userID, final int NotificationID);

    List<NotificationLog> getNotificationLogByUser(final int userID);

    void insertNotificationLog(final NotificationLog NotificationLog);

    void updateNotificationLog(final int userID, final int NotificationID, final NotificationLog updatedLog);

    void deleteNotificationLog(final int userID, final int NotificationID);
}
