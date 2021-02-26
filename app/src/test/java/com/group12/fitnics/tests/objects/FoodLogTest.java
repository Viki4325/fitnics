package com.group12.fitnics.tests.objects;

import com.group12.fitnics.objects.FoodLog;
import com.group12.fitnics.objects.MyDate;

import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FoodLogTest {

    @Test
    public void testFoodLogCreation() {
        System.out.println("\nStarting testFoodLogCreation");
        MyDate date = new MyDate(new GregorianCalendar(2021, 1, 1));

        FoodLog log = new FoodLog(0, 3, date, 200);
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
        MyDate date1 = new MyDate(new GregorianCalendar(2021, 1, 1));
        MyDate date2 = new MyDate(new GregorianCalendar(2021, 1, 2));

        FoodLog log = new FoodLog(0, 3, date1, 200);
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
}
