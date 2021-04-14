package com.group12.fitnics.tests.business;

import com.group12.fitnics.business.AccessNotification;
import com.group12.fitnics.exceptions.InvalidNotificationException;
import com.group12.fitnics.exceptions.NotificationNotFoundException;
import com.group12.fitnics.objects.Notification;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AccessNotificationTest {
    AccessNotification accessNotification;

    @Before
    public void setup() {
        accessNotification = new AccessNotification();
    }

    @Test
    public void testNotificationById(){
        System.out.println("\nStarting testNotificationById...");

        int lastId = 6; //Because there are 7 objects in total
        assertNotEquals(accessNotification.getNotificationById(3).getHour(),7);
        assertEquals(accessNotification.getNotificationById(3).getHour(),18);
        assertEquals(accessNotification.getNotificationById(lastId).getHour(),22);
        System.out.println("Finish testNotificationById");
    }

    @Test
    public void testInsertExercise(){
        System.out.println("\nStarting testInsertNotification...");

        Notification new_1 = new Notification("title",7,30,true);
        new_1.setNotificationID();

        accessNotification.insertNotification(new_1);
        int id = new_1.getNotificationID();
        assertEquals(accessNotification.getNotificationById(id).getHour(), new_1.getHour());

        System.out.println("Finished testInsertNotification");
    }

    @Test(expected = InvalidNotificationException.class)
    public void testInsertNotificationNull() {
        System.out.println("\nStarting testInsertNotificationNull...");
        accessNotification.insertNotification(null);
        System.out.println("Finished testInsertNotificationNull");
    }

    @Test
    public void testDeleteNotificationById(){
        System.out.println("\nStarting testDeleteNotificationById...");

        Notification new_1 = new Notification("title",7,30,true);
        new_1.setNotificationID();

        accessNotification.insertNotification(new_1);
        assertEquals(accessNotification.getNotificationById(new_1.getNotificationID()).getHour(),new_1.getHour());
        accessNotification.deleteNotificationById(new_1.getNotificationID());
        assertNotEquals(accessNotification.getNotificationById(new_1.getNotificationID()),new_1.getNotificationID());

        System.out.println("Finished testDeleteNotificationById");

    }

    @Test(expected = InvalidNotificationException.class)
    public void testDeleteNotificationByInvalidID() {
        System.out.println("\nStarting testDeleteNotificationByInvalidID...");
        accessNotification.deleteNotificationById(-1);
        System.out.println("Finished testDeleteNotificationByInvalidID");
    }

    @Test(expected = NotificationNotFoundException.class)
    public void testDeleteNotificationNotFoundByID() {
        System.out.println("\nStarting testDeleteNotificationNotFoundByID...");
        accessNotification.deleteNotificationById(88);
        System.out.println("Finished testDeleteNotificationNotFoundByID");
    }


}
