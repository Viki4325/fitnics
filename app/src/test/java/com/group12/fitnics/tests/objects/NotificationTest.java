package com.group12.fitnics.tests.objects;



import com.group12.fitnics.objects.Notification;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NotificationTest {
    Notification notify;

    @Before
    public void setUp() {
        System.out.println("Starting test for Notification");

        notify = new Notification("Test",1,0,true);
    }

    @Test
    public void testNotificationCreation() {
        System.out.println("\nStarting testNotificationCreation");

        assertNotNull(notify);
        assertEquals("Test", notify.getTitle());
        notify.setNotificationID();
        assertEquals(0, notify.getNotificationID());
        assertEquals(1, notify.getHour());
        assertEquals(0, notify.getMinute());
        assertEquals(true,notify.isActive());

        System.out.println("Finished testNotificationCreation");
    }

    @Test
    public void testNotificationSetters() {
        System.out.println("\nStarting testNotificationSetters");

        notify.setTitle("new title");
        assertEquals("new title", notify.getTitle());
        notify.setNotificationID(1);
        assertEquals(1,notify.getNotificationID());
        notify.setHour(5);
        assertEquals(5,notify.getHour());
        notify.setMinute(30);
        assertEquals(30,notify.getMinute());
        notify.setIsActive(false);
        assertEquals(false,notify.isActive());

        System.out.println("Finished testNotificationSetters");
    }
}
