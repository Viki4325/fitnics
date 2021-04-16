package com.group12.fitnics.tests.business;



import com.group12.fitnics.business.AccessNotificationLogs;
import com.group12.fitnics.exceptions.InvalidNotificationLogException;
import com.group12.fitnics.exceptions.NotificationLogNotFoundException;
import com.group12.fitnics.objects.NotificationLog;
import com.group12.fitnics.persistence.stub.NotificationLogPersistenceStub;
import com.group12.fitnics.persistence.stub.NotificationPersistenceStub;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


public class AccessNotificationLogTest {
    AccessNotificationLogs accessNotificationLogs;

    @Before
    public void setUp() {
        System.out.println("Starting test for AccessNotificationLogs");
        accessNotificationLogs = new AccessNotificationLogs(new NotificationLogPersistenceStub(), new NotificationPersistenceStub());

    }

    @Test
    public void testGetNotificationLog() {
        System.out.println("\nStarting testGetNotificationLog");
        NotificationLog log = accessNotificationLogs.getNotificationLog(0, 0);

        assertNotNull(log);
        assertEquals(0, log.getUserID());
        assertEquals(0, log.getNotificationID());
        assertEquals(12, log.getHour());

        log = accessNotificationLogs.getNotificationLog(0, 4); // not found
        assertNull(log);
        log = accessNotificationLogs.getNotificationLog(7, 7); // not found
        assertNull(log);

        System.out.println("Finished testGetNotificationLog");
    }

    @Test
    public void testGetNotificationLogByUser() {
        System.out.println("\nStarting testGetNotificationLogByUser");
        List<NotificationLog> lists = accessNotificationLogs.getNotificationLogByUser(2);

        assertNotNull(lists);
        assertEquals(3, lists.size());
        for (int i = 0; i < lists.size(); i++) {
            assertEquals(2, lists.get(i).getUserID());
        }

        lists = accessNotificationLogs.getNotificationLogByUser(4); // not found
        assertEquals(0, lists.size());

        System.out.println("Finished testGetNotificationLogByUser");
    }

    @Test
    public void testInsertNotificationLog() {
        System.out.println("\nStarting testInsertNotificationLog");
        NotificationLog log1 = new NotificationLog("test", 2, 7,6,30);

        accessNotificationLogs.insertNotificationLog(log1);
        assertEquals(2, accessNotificationLogs.getNotificationLog(2, 7).getUserID());
        assertEquals(7, accessNotificationLogs.getNotificationLog(2, 7).getNotificationID());
        assertEquals(6, accessNotificationLogs.getNotificationLog(2,7).getHour());

        // delete what we just inserted
        accessNotificationLogs.deleteNotificationLog(2, 7);
        assertNull(accessNotificationLogs.getNotificationLog(2, 7));

        System.out.println("Finished testInsertNotificationLog");
    }

    @Test(expected = InvalidNotificationLogException.class)
    public void testInsertNotificationLogNull() {
        System.out.println("\nStarting testInsertNotificationLogNull");
        accessNotificationLogs.insertNotificationLog(null);
        System.out.println("Finished testInsertNotificationLogNull");
    }

    @Test(expected = InvalidNotificationLogException.class)
    public void testInsertNotificationLogInvalidUserID() {
        System.out.println("\nStarting testInsertNotificationLogInvalidUserID");
        NotificationLog log2 = new NotificationLog("empty",-1, 2,7,45); // invalid userID
        accessNotificationLogs.insertNotificationLog(log2);
        System.out.println("Finished testInsertNotificationLogInvalidUserID");
    }

    @Test(expected = InvalidNotificationLogException.class)
    public void testInsertNotificationLogInvalidNotificationID() {
        System.out.println("\nStarting testInsertNotificationLogInvalidFoodID");
        NotificationLog log3 = new NotificationLog("invalid",1, -2, 7, 45); // invalid foodID
        accessNotificationLogs.insertNotificationLog(log3);
        System.out.println("Finished testInsertNotificationLogInvalidFoodID");
    }

    @Test(expected = InvalidNotificationLogException.class)
    public void testInsertDuplicateNotificationLog() {
        System.out.println("\nStarting testInsertDuplicateNotificationLog");
        NotificationLog log5 = new NotificationLog("Workout",0, 0, 12, 10); // food log that already exists
        accessNotificationLogs.insertNotificationLog(log5);
        System.out.println("Finished testInsertDuplicateNotificationLog");
    }

    @Test
    public void testDeleteNotificationLog() {
        System.out.println("\nStarting testDeleteNotificationLog");

        accessNotificationLogs.deleteNotificationLog(0, 1);
        assertNull(accessNotificationLogs.getNotificationLog(0, 1));

        // add what we just deleted
        accessNotificationLogs.insertNotificationLog(new NotificationLog("Breakfast",0, 1, 7, 30));
        assertEquals(7, accessNotificationLogs.getNotificationLog(0, 1).getHour());
        assertEquals(2, accessNotificationLogs.getNotificationLogByUser(0).size());

        System.out.println("Finished testDeleteNotificationLog");
    }

    @Test(expected = NotificationLogNotFoundException.class)
    public void testDeleteNotFoundNotificationLog1() {
        System.out.println("\nStarting testDeleteNotFoundNotificationLog1 - User ID is the same, but NotificationID is different.");
        accessNotificationLogs.deleteNotificationLog(0, 4); // not found
        System.out.println("Finished testDeleteNotFoundNotificationLog1");
    }

}
