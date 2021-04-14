package com.group12.fitnics.tests.objects;

import com.group12.fitnics.objects.NotificationLog;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class NotificationLogTest {
    NotificationLog log;


    @Before
    public void setUp() {
        System.out.println("Starting test for NotificationLogs");

        log = new NotificationLog("Test",0,0,1,0);
    }

    @Test
    public void testNotificationLogCreation() {
        System.out.println("\nStarting testNotificationLogCreation");

        assertNotNull(log);
        assertEquals("Test", log.getTitle());
        assertEquals(0, log.getUserID());
        assertEquals(0, log.getNotificationID());
        assertEquals(1, log.getHour());
        assertEquals(0, log.getMinutes());

        System.out.println("Finished testNotificationLogCreation");
    }

    @Test
    public void testNotificationLogSetters() {
        System.out.println("\nStarting testNotificationLogSetters");

        log.setTitle("new title");
        assertEquals("new title", log.getTitle());
        log.setUserID(1);
        assertEquals(1, log.getUserID());
        log.setNotificationID(1);
        assertEquals(1, log.getNotificationID());
        log.setHour(5);
        assertEquals(5, log.getHour());
        log.setMinutes(20);
        assertEquals(20, log.getMinutes());

        System.out.println("Finished testNotificationLogSetters");
    }

}
