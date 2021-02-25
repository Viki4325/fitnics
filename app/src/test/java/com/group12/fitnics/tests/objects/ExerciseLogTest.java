package com.group12.fitnics.tests.objects;

import com.group12.fitnics.objects.ExerciseLog;
import com.group12.fitnics.objects.MyDate;

import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ExerciseLogTest {

    @Test
    public void testExerciseLogCreation() {
        System.out.println("\nStarting testExerciseLogCreation");
        MyDate date = new MyDate(new GregorianCalendar(2021, 1, 1));

        ExerciseLog log = new ExerciseLog(0, 3, date, 10);
        assertNotNull(log);
        assertEquals(0, log.getUserID());
        assertEquals(3, log.getExerciseID());
        assertEquals(date, log.getDate());
        assertEquals(10, log.getMinutes());

        System.out.println("Finished testExerciseLogCreation");
    }

    @Test
    public void testExerciseLogSetters() {
        System.out.println("\nStarting testExerciseLogSetters");
        MyDate date1 = new MyDate(new GregorianCalendar(2021, 1, 1));
        MyDate date2 = new MyDate(new GregorianCalendar(2021, 1, 2));

        ExerciseLog log = new ExerciseLog(0, 3, date1, 10);
        log.setUserID(1);
        assertEquals(1, log.getUserID());
        log.setExerciseIDID(1);
        assertEquals(1, log.getExerciseID());
        log.setDate(date2);
        assertEquals(date2, log.getDate());
        log.setMinutes(20);
        assertEquals(20, log.getMinutes());

        System.out.println("Finished testExerciseLogSetters");
    }
}
