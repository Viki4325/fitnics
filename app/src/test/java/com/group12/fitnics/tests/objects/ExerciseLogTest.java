package com.group12.fitnics.tests.objects;

import com.group12.fitnics.objects.ExerciseLog;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ExerciseLogTest {

    private LocalDate date;
    private ExerciseLog log;

    @Before
    public void setUp() {
        System.out.println("Starting test for AccessFoodLogs");
        date = LocalDate.of(2021, 2, 1);
        log = new ExerciseLog(0, 3, date, 10);
    }

    @Test
    public void testExerciseLogCreation() {
        System.out.println("\nStarting testExerciseLogCreation");

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
        LocalDate date2 = LocalDate.of(2021, 2, 2);

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

    @Test
    public void testExerciseLogDateString() {
        System.out.println("\nStarting testExerciseLogDateString");
        LocalDate date2 = LocalDate.of(2021, 11, 8);

        assertEquals("2021-02-01", log.getDateString());

        log.setDateString("2021-11-08");
        assertEquals(date2, log.getDate());

        System.out.println("Finished testExerciseLogDateString");
    }
}
