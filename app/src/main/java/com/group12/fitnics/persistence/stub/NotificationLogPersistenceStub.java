package com.group12.fitnics.persistence.stub;


import com.group12.fitnics.objects.NotificationLog;
import com.group12.fitnics.persistence.INotificationLogPersistence;

import java.util.ArrayList;
import java.util.List;

public class NotificationLogPersistenceStub implements INotificationLogPersistence {
    private List<NotificationLog> NotificationLogs;

    public NotificationLogPersistenceStub() {
        NotificationLogs = new ArrayList<>();

        NotificationLogs.add(new NotificationLog("Workout",0, 0, 12, 10));
        NotificationLogs.add(new NotificationLog("Breakfast",0, 1, 7, 30));
        NotificationLogs.add(new NotificationLog("Lunch",1, 2, 13, 0));
        NotificationLogs.add(new NotificationLog("Dinner",2, 3, 18, 0));
        NotificationLogs.add(new NotificationLog("Workout",2, 4, 8, 0));
        NotificationLogs.add(new NotificationLog("Snack",2, 5, 16, 30));
        NotificationLogs.add(new NotificationLog("Sleep",3, 6, 22, 55));
    }

    @Override
    public NotificationLog getNotificationLog(int userID, int NotificationID) {
        boolean found = false;
        NotificationLog result = null;
        for (int i = 0; i < NotificationLogs.size() && !found; i++) {
            NotificationLog log = NotificationLogs.get(i);
            if (log.getUserID() == userID && log.getNotificationID() == NotificationID) {
                found = true;
                result = log;
            }
        }
        return result;
    }

    @Override
    public List<NotificationLog> getNotificationLogByUser(int userID) {
        List<NotificationLog> list = new ArrayList<>();
        for (int i = 0; i < NotificationLogs.size(); i++) {
            if (NotificationLogs.get(i).getUserID() == userID)
                list.add(NotificationLogs.get(i));
        }
        return list;
    }

    @Override
    public void insertNotificationLog(NotificationLog NotificationLog) {

        NotificationLogs.add(NotificationLog);
    }

    @Override
    public void updateNotificationLog(int userID, int NotificationID, NotificationLog updatedLog) {
        boolean found = false;
        for (int i = 0; i < NotificationLogs.size() && !found; i++) {
            NotificationLog log = NotificationLogs.get(i);
            if (log.getUserID() == userID && log.getNotificationID() == NotificationID) {
                NotificationLogs.set(i, updatedLog);
                found = true;
            }
        }
    }

    @Override
    public void deleteNotificationLog(int userID, int NotificationID) {
        boolean found = false;
        for (int i = 0; i < NotificationLogs.size() && !found; i++) {
            NotificationLog log = NotificationLogs.get(i);
            if (log.getUserID() == userID && log.getNotificationID() == NotificationID) {
                NotificationLogs.remove(i);
                found = true;
            }
        }
    }
}
