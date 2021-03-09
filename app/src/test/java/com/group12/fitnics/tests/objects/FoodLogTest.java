package com.group12.fitnics.tests.objects;

import com.group12.fitnics.objects.FoodLog;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FoodLogTest {

    private LocalDate date;
    private FoodLog log;

    @Before
    public void setUp() {
        System.out.println("Starting test for AccessFoodLogs");
        date = LocalDate.of(2021, 2, 1);
        log = new FoodLog(0, 3, date, 200);
    }

    @Test
    public void testFoodLogCreation() {
        System.out.println("\nStarting testFoodLogCreation");

        assertNotNull(log);
        assertEquals(0, log.getUserID());
        assertEquals(3, log.getFoodID());
        assertEquals(date, log.getDate());
        assertEquals(200, log.getGrams());

        System.out.println("Finished testFoodLogCreation");
    }

    @Test
    public void testFoodLogSetters() {
        System.out.println("\nStarting testFoodLogSetters");
        LocalDate date2 = LocalDate.of(2021, 2, 2);

        log.setUserID(1);
        assertEquals(1, log.getUserID());
        log.setFoodID(1);
        assertEquals(1, log.getFoodID());
        log.setDate(date2);
        assertEquals(date2, log.getDate());
        log.setGrams(100);
        assertEquals(100, log.getGrams());

        System.out.println("Finished testFoodLogSetters");
    }

    @Test
    public void testFoodLogDateString() {
        System.out.println("\nStarting testFoodLogDateString");
        LocalDate date2 = LocalDate.of(2021, 11, 8);

        assertEquals("2021-02-01", log.getDateString());

        log.setDateString("2021-11-08");
        assertEquals(date2, log.getDate());

        System.out.println("Finished testFoodLogDateString");
    }
}
