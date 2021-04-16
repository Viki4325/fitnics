package com.group12.fitnics.business;

import com.group12.fitnics.application.Services;
import com.group12.fitnics.exceptions.InvalidNotificationLogException;
import com.group12.fitnics.exceptions.NotificationLogNotFoundException;
import com.group12.fitnics.objects.NotificationLog;
import com.group12.fitnics.persistence.INotificationLogPersistence;
import com.group12.fitnics.persistence.INotificationPersistence;
import com.group12.fitnics.persistence.stub.NotificationLogPersistenceStub;

import java.util.List;

public class AccessNotificationLogs {
    private INotificationLogPersistence NotificationLogPersistence;
    private INotificationPersistence NotificationPersistence;

    public AccessNotificationLogs() {
        NotificationLogPersistence = Services.getNotificationLogPersistence();
        NotificationPersistence = Services.getNotificationPersistence();
    }

    public AccessNotificationLogs(INotificationLogPersistence nl, INotificationPersistence n) {
        this.NotificationLogPersistence = nl;
        this.NotificationPersistence = n;
    }

    public NotificationLog getNotificationLog(int userID, int NotificationID) {
        return NotificationLogPersistence.getNotificationLog(userID, NotificationID);
    }

    public List<NotificationLog> getNotificationLogByUser(int userID) {
        return NotificationLogPersistence.getNotificationLogByUser(userID);
    }

    public void insertNotificationLog(NotificationLog NotificationLog) throws InvalidNotificationLogException {
        if (!checkInvariant(NotificationLog))
            throw new InvalidNotificationLogException("The Notification log has invalid userID or NotificationID or minutes. ");

        // if there exists same exercise log already, to not allow it to be inserted
        if (getNotificationLog(NotificationLog.getUserID(), NotificationLog.getNotificationID()) != null)
            throw new InvalidNotificationLogException("The Notification log is duplicate. You could instead increase the time for this Notification from the logs. Thank you!");

        NotificationLogPersistence.insertNotificationLog(NotificationLog);
    }

    public void deleteNotificationLog(int userID, int NotificationID) throws NotificationLogNotFoundException {
        if (getNotificationLog(userID, NotificationID) == null)
            throw new NotificationLogNotFoundException("There is no such Notification log to update. ");

        NotificationLogPersistence.deleteNotificationLog(userID, NotificationID);
    }

    private boolean checkInvariant(NotificationLog NotificationLog) {
        if (NotificationLog == null || NotificationLog.getUserID() < 0 || NotificationLog.getNotificationID() < 0 || NotificationLog.getMinutes() < 0 || NotificationLog.getMinutes() > 60
        || NotificationLog.getHour() <= 0 || NotificationLog.getHour() > 24)
            return false;
        return true;
    }
}
