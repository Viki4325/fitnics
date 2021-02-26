package com.group12.fitnics.tests.business;

import com.group12.fitnics.business.AccessFoodLogs;
import com.group12.fitnics.objects.FoodLog;
import com.group12.fitnics.objects.MyDate;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.List;

public class AccessFoodLogsTest extends TestCase {

    private AccessFoodLogs accessFoodLogs;

    @Before
    public void setUp() {
        System.out.println("Starting test for AccessFoodLogs");
        accessFoodLogs = new AccessFoodLogs();
    }

    @Test
    public void testGetFoodLog() {
        System.out.println("\nStarting testGetFoodLog");
        MyDate date = new MyDate(new GregorianCalendar(2021, 0, 1));
        FoodLog log = accessFoodLogs.getFoodLog(0, 1, date);

        assertNotNull(log);
        assertEquals(0, log.getUserID());
        assertEquals(1, log.getFoodID());
        assertEquals(date.toString(), log.getDate().toString());
        assertEquals(25, log.getGrams());

        log = accessFoodLogs.getFoodLog(0, 4, date); // not found
        assertNull(log);
        log = accessFoodLogs.getFoodLog(2, 3, date); // not found
        assertNull(log);

        System.out.println("Finished testGetFoodLog");
    }

    @Test
    public void testGetFoodLogByUser() {
        System.out.println("\nStarting testGetFoodLogByUser");
        List<FoodLog> lists = accessFoodLogs.getFoodLogByUser(2);

        assertNotNull(lists);
        assertEquals(6, lists.size());
        for (int i = 0; i < lists.size(); i++) {
            assertEquals(2, lists.get(i).getUserID());
        }

        lists = accessFoodLogs.getFoodLogByUser(4); // not found
        assertEquals(0, lists.size());

        System.out.println("Finished testGetFoodLogByUser");
    }

    @Test
    public void testGetFoodLogByUserDate() {
        System.out.println("\nStarting testGetFoodLogByUserDate");
        MyDate date = new MyDate(new GregorianCalendar(2021, 0, 1));
        List<FoodLog> lists = accessFoodLogs.getFoodLogByUserDate(2, date);

        assertNotNull(lists);
        assertEquals(4, lists.size());
        for (int i = 0; i < lists.size(); i++) {
            assertEquals(2, lists.get(i).getUserID());
        }

        lists = accessFoodLogs.getFoodLogByUserDate(1, date); // not found
        assertEquals(0, lists.size());
        lists = accessFoodLogs.getFoodLogByUserDate(3, date); // not found
        assertEquals(0, lists.size());

        System.out.println("Finished testGetFoodLogByUserDate");
    }

    @Test
    public void testInsertFoodLog() {
        System.out.println("\nStarting testInsertFoodLog");
        MyDate date = new MyDate(new GregorianCalendar(2021, 0, 1));
        FoodLog log1 = new FoodLog(1, 2, date, 200);
        FoodLog log2 = new FoodLog(-1, 2, date, 200); // invalid food log
        FoodLog log3 = new FoodLog(1, -2, date, 200); // invalid food log
        FoodLog log4 = new FoodLog(1, 2, date, -10); // invalid food log
        FoodLog log5 = new FoodLog(0, 1, date, 77); // food log that already exists

        String result = accessFoodLogs.insertFoodLog(log1);
        assertEquals(1, accessFoodLogs.getFoodLogByUserDate(1, date).size());
        assertEquals("Success", result);

        result = accessFoodLogs.insertFoodLog(log2);
        assertEquals("Fail", result);
        result = accessFoodLogs.insertFoodLog(log3);
        assertEquals("Fail", result);
        result = accessFoodLogs.insertFoodLog(log4);
        assertEquals("Fail", result);
        result = accessFoodLogs.insertFoodLog(log5);
        assertEquals("Fail", result);
        result = accessFoodLogs.insertFoodLog(null);
        assertEquals("Fail", result);

        // delete what we just inserted
        assertEquals("Success", accessFoodLogs.deleteFoodLog(1, 2, date));
        assertNull(accessFoodLogs.getFoodLog(1, 2, date));

        System.out.println("Finished testInsertFoodLog");
    }

    @Test
    public void testDeleteFoodLog() {
        System.out.println("\nStarting testDeleteFoodLog");
        MyDate date = new MyDate(new GregorianCalendar(2021, 0, 1));

        String result = accessFoodLogs.deleteFoodLog(0, 1, date);
        assertNull(accessFoodLogs.getFoodLog(0, 1, date));
        assertEquals("Success", result);

        result = accessFoodLogs.deleteFoodLog(0, 4, date); // not found
        assertEquals("Fail", result);
        result = accessFoodLogs.deleteFoodLog(1, 3, date); // not found
        assertEquals("Fail", result);

        // add what we just deleted
        assertEquals("Success", accessFoodLogs.insertFoodLog(new FoodLog(0, 1, date, 25)));
        assertEquals(2, accessFoodLogs.getFoodLogByUserDate(0, date).size());

        System.out.println("Finished testDeleteFoodLog");
    }

    @Test
    public void testUpdateFoodLog() {
        System.out.println("\nStarting testUpdateFoodLog");
        MyDate date = new MyDate(new GregorianCalendar(2021, 0, 1));
        FoodLog log1 = new FoodLog(0, 1, date, 100);
        FoodLog log2 = new FoodLog(-1, 2, date, 200); // invalid food log
        FoodLog log3 = new FoodLog(1, -2, date, 200); // invalid food log
        FoodLog log4 = new FoodLog(1, 2, date, -10); // invalid food log

        String result = accessFoodLogs.updateFoodLog(0, 1, date, log1);
        assertEquals("Success", result);
        assertEquals(100, accessFoodLogs.getFoodLog(0, 1, date).getGrams());

        result = accessFoodLogs.updateFoodLog(0, 4, date, log1); // not found
        assertEquals("Fail", result);
        result = accessFoodLogs.updateFoodLog(0, 1, date, log2);
        assertEquals("Fail", result);
        result = accessFoodLogs.updateFoodLog(0, 1, date, log3);
        assertEquals("Fail", result);
        result = accessFoodLogs.updateFoodLog(0, 1, date, log4);
        assertEquals("Fail", result);

        // un-do the update
        result = accessFoodLogs.updateFoodLog(0, 1, date, new FoodLog(0, 1, date,25));
        assertEquals("Success", result);
        assertEquals(25, accessFoodLogs.getFoodLog(0, 1, date).getGrams());

        System.out.println("Finished testUpdateFoodLog");
    }


    @Test
    public void testGetUserTotalDailyIntake() {
        System.out.println("\nStarting testGetUserTotalDailyIntake");
        MyDate date1 = new MyDate(new GregorianCalendar(2021, 0, 1));
        MyDate date2 = new MyDate(new GregorianCalendar(2021, 7, 19));

        int result = accessFoodLogs.getUserTotalDailyIntake(0, date1);
        assertEquals(164, result);
        result = accessFoodLogs.getUserTotalDailyIntake(2, date1);
        assertEquals(699, result);

        result = accessFoodLogs.getUserTotalDailyIntake(3, date1); // no logs
        assertEquals(0, result);
        result = accessFoodLogs.getUserTotalDailyIntake(0, date2); // no logs
        assertEquals(0, result);

        System.out.println("Finished testGetUserTotalDailyIntake");
    }

}
