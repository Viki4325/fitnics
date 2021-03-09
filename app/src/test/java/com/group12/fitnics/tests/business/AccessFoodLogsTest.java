package com.group12.fitnics.tests.business;

import com.group12.fitnics.business.AccessFoodLogs;
import com.group12.fitnics.exceptions.FoodLogNotFoundException;
import com.group12.fitnics.exceptions.InvalidFoodLogException;
import com.group12.fitnics.objects.FoodLog;
import com.group12.fitnics.objects.MyDate;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.List;

public class AccessFoodLogsTest {

    private AccessFoodLogs accessFoodLogs;
    private LocalDate date;

    @Before
    public void setUp() {
        System.out.println("Starting test for AccessFoodLogs");
        accessFoodLogs = new AccessFoodLogs();
        date = LocalDate.of(2021, 1, 1);
    }

    @Test
    public void testGetFoodLog() {
        System.out.println("\nStarting testGetFoodLog");
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
        FoodLog log1 = new FoodLog(1, 2, date, 200);

        accessFoodLogs.insertFoodLog(log1);
        assertEquals(log1, accessFoodLogs.getFoodLog(1, 2, date));
        assertEquals(1, accessFoodLogs.getFoodLogByUserDate(1, date).size());

        // delete what we just inserted
        accessFoodLogs.deleteFoodLog(1, 2, date);
        assertNull(accessFoodLogs.getFoodLog(1, 2, date));

        System.out.println("Finished testInsertFoodLog");
    }


    @Test(expected = InvalidFoodLogException.class)
    public void testInsertFoodLogNull() {
        System.out.println("\nStarting testInsertFoodLogNull");
        accessFoodLogs.insertFoodLog(null);
        System.out.println("Finished testInsertFoodLogNull");
    }

    @Test(expected = InvalidFoodLogException.class)
    public void testInsertFoodLogInvalidUserID() {
        System.out.println("\nStarting testInsertFoodLogInvalidUserID");
        FoodLog log2 = new FoodLog(-1, 2, date, 200); // invalid userID
        accessFoodLogs.insertFoodLog(log2);
        System.out.println("Finished testInsertFoodLogInvalidUserID");
    }

    @Test(expected = InvalidFoodLogException.class)
    public void testInsertFoodLogInvalidFoodID() {
        System.out.println("\nStarting testInsertFoodLogInvalidFoodID");
        FoodLog log3 = new FoodLog(1, -2, date, 200); // invalid foodID
        accessFoodLogs.insertFoodLog(log3);
        System.out.println("Finished testInsertFoodLogInvalidFoodID");
    }

    @Test(expected = InvalidFoodLogException.class)
    public void testInsertFoodLogInvalidGrams() {
        System.out.println("\nStarting testInsertFoodLogInvalidGrams");
        FoodLog log4 = new FoodLog(1, 2, date, -10); // invalid grams
        accessFoodLogs.insertFoodLog(log4);
        System.out.println("Finished testInsertFoodLogInvalidGrams");
    }

    @Test(expected = InvalidFoodLogException.class)
    public void testInsertDuplicateFoodLog() {
        System.out.println("\nStarting testInsertDuplicateFoodLog");
        FoodLog log5 = new FoodLog(0, 1, date, 77); // food log that already exists
        accessFoodLogs.insertFoodLog(log5);
        System.out.println("Finished testInsertDuplicateFoodLog");
    }

    @Test
    public void testUpdateFoodLog() {
        System.out.println("\nStarting testUpdateFoodLog");
        FoodLog log1 = new FoodLog(0, 1, date, 100);

        accessFoodLogs.updateFoodLog(0, 1, date, log1);
        assertEquals(100, accessFoodLogs.getFoodLog(0, 1, date).getGrams());

        // un-do the update
        accessFoodLogs.updateFoodLog(0, 1, date, new FoodLog(0, 1, date,25));
        assertEquals(25, accessFoodLogs.getFoodLog(0, 1, date).getGrams());

        System.out.println("Finished testUpdateFoodLog");
    }

    @Test(expected = InvalidFoodLogException.class)
    public void testUpdateFoodLogNull() {
        System.out.println("\nStarting testUpdateFoodLogNull");
        accessFoodLogs.updateFoodLog(0, 1, date, null);
        System.out.println("Finished testUpdateFoodLogInvalidUserID");
    }

    @Test(expected = InvalidFoodLogException.class)
    public void testUpdateFoodLogInvalidUserID() {
        System.out.println("\nStarting testUpdateFoodLogInvalidUserID");
        FoodLog log2 = new FoodLog(-1, 2, date, 200); // invalid user id
        accessFoodLogs.updateFoodLog(0, 1, date, log2);
        System.out.println("Finished testUpdateFoodLogInvalidUserID");
    }

    @Test(expected = InvalidFoodLogException.class)
    public void testUpdateFoodLogInvalidFoodID() {
        System.out.println("\nStarting testUpdateFoodLogInvalidFoodID");
        FoodLog log3 = new FoodLog(1, -2, date, 200); // invalid food id
        accessFoodLogs.updateFoodLog(0, 1, date, log3);
        System.out.println("Finished testUpdateFoodLogInvalidFoodID");
    }

    @Test(expected = InvalidFoodLogException.class)
    public void testUpdateFoodLogInvalidGrams() {
        System.out.println("\nStarting testUpdateFoodLogInvalidGrams");
        FoodLog log4 = new FoodLog(1, 2, date, -10); // invalid grams
        accessFoodLogs.updateFoodLog(0, 1, date, log4);
        System.out.println("Finished testUpdateFoodLogInvalidGrams");
    }

    @Test(expected = FoodLogNotFoundException.class)
    public void testUpdateNotFoundFoodLog() {
        System.out.println("\nStarting testUpdateNotFoundFoodLog");
        FoodLog log1 = new FoodLog(0, 1, date, 100);
        accessFoodLogs.updateFoodLog(0, 4, date, log1); // not found
        System.out.println("Finished testUpdateNotFoundFoodLog");
    }

    @Test
    public void testDeleteFoodLog() {
        System.out.println("\nStarting testDeleteFoodLog");

        accessFoodLogs.deleteFoodLog(0, 1, date);
        assertNull(accessFoodLogs.getFoodLog(0, 1, date));

        // add what we just deleted
        accessFoodLogs.insertFoodLog(new FoodLog(0, 1, date, 25));
        assertEquals(25, accessFoodLogs.getFoodLog(0, 1, date).getGrams());
        assertEquals(2, accessFoodLogs.getFoodLogByUserDate(0, date).size());

        System.out.println("Finished testDeleteFoodLog");
    }

    @Test(expected = FoodLogNotFoundException.class)
    public void testDeleteNotFoundFoodLog1() {
        System.out.println("\nStarting testDeleteNotFoundFoodLog1 - User ID and date are the same, but foodID is different.");
        accessFoodLogs.deleteFoodLog(0, 4, date); // not found
        System.out.println("Finished testDeleteNotFoundFoodLog1");
    }

    @Test(expected = FoodLogNotFoundException.class)
    public void testDeleteNotFoundFoodLog2() {
        System.out.println("\nStarting testDeleteNotFoundFoodLog2 - User ID and foodID are the same, but date is different.");
        accessFoodLogs.deleteFoodLog(1, 3, date); // not found
        System.out.println("Finished testDeleteNotFoundFoodLog2");
    }

    @Test
    public void testGetUserTotalDailyIntake() {
        System.out.println("\nStarting testGetUserTotalDailyIntake");
//        MyDate date1 = new MyDate(new GregorianCalendar(2021, 0, 1));
//        MyDate date2 = new MyDate(new GregorianCalendar(2021, 7, 19));
        LocalDate date1 = LocalDate.of(2021, 1, 1);
        LocalDate date2 = LocalDate.of(2021, 8, 19);

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
