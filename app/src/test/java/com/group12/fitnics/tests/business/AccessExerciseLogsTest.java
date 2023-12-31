package com.group12.fitnics.tests.business;

import com.group12.fitnics.business.AccessExerciseLogs;
import com.group12.fitnics.exceptions.ExerciseLogNotFoundException;
import com.group12.fitnics.exceptions.InvalidExerciseLogException;
import com.group12.fitnics.exceptions.InvalidFoodLogException;
import com.group12.fitnics.objects.ExerciseLog;
import com.group12.fitnics.objects.MyDate;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.List;

public class AccessExerciseLogsTest {

    private AccessExerciseLogs accessExerciseLogs;
    private MyDate date;

    @Before
    public void setUp() {
        System.out.println("Starting test for AccessExerciseLogs");
        accessExerciseLogs = new AccessExerciseLogs();
        date = new MyDate(new GregorianCalendar(2021, 0, 1));
    }

    @Test
    public void testGetExerciseLog() {
        System.out.println("\nStarting testGetExerciseLog");
        ExerciseLog log = accessExerciseLogs.getExerciseLog(0, 2, date);

        assertNotNull(log);
        assertEquals(0, log.getUserID());
        assertEquals(2, log.getExerciseID());
        assertEquals(date.toString(), log.getDate().toString());
        assertEquals(10, log.getMinutes());

        log = accessExerciseLogs.getExerciseLog(0, 3, date); // not found
        assertNull(log);
        log = accessExerciseLogs.getExerciseLog(2, 0, date); // not found
        assertNull(log);

        System.out.println("Finished testGetExerciseLog");
    }

    @Test
    public void testGetExerciseLogByUser() {
        System.out.println("\nStarting testGetExerciseLogByUser");
        List<ExerciseLog> lists = accessExerciseLogs.getExerciseLogByUser(2);

        assertNotNull(lists);
        assertEquals(3, lists.size());
        for (int i = 0; i < lists.size(); i++) {
            assertEquals(2, lists.get(i).getUserID());
        }

        lists = accessExerciseLogs.getExerciseLogByUser(4); // not found
        assertEquals(0, lists.size());

        System.out.println("Finished testGetExerciseLogByUser");
    }

    @Test
    public void testGetExerciseLogByUserDate() {
        System.out.println("\nStarting testGetExerciseLogByUserDate");
        List<ExerciseLog> lists = accessExerciseLogs.getExerciseLogByUserDate(2, date);

        assertNotNull(lists);
        assertEquals(2, lists.size());
        for (int i = 0; i < lists.size(); i++) {
            assertEquals(2, lists.get(i).getUserID());
        }

        lists = accessExerciseLogs.getExerciseLogByUserDate(3, date); // not found
        assertEquals(0, lists.size());
        lists = accessExerciseLogs.getExerciseLogByUserDate(4, date); // not found
        assertEquals(0, lists.size());

        System.out.println("Finished testGetExerciseLogByUserDate");
    }

    @Test
    public void testInsertExerciseLog() {
        System.out.println("\nStarting testInsertExerciseLog");
        ExerciseLog log1 = new ExerciseLog(0, 1, date, 10);

        accessExerciseLogs.insertExerciseLog(log1);
        assertEquals(10, accessExerciseLogs.getExerciseLog(0, 1, date).getMinutes());
        assertEquals(2, accessExerciseLogs.getExerciseLogByUserDate(0, date).size());

        // delete what we just inserted
        accessExerciseLogs.deleteExerciseLog(0, 1, date);
        assertNull(accessExerciseLogs.getExerciseLog(0, 1, date));

        System.out.println("Finished testInsertExerciseLog");
    }

    @Test(expected = InvalidExerciseLogException.class)
    public void testInsertExerciseLogNull() {
        System.out.println("\nStarting testInsertExerciseLogNull");
        accessExerciseLogs.insertExerciseLog(null);
        System.out.println("Finished testInsertExerciseLogNull");
    }

    @Test(expected = InvalidExerciseLogException.class)
    public void testInsertExerciseLogInvalidUserID() {
        System.out.println("\nStarting testInsertExerciseLogInvalidUserID");
        ExerciseLog log2 = new ExerciseLog(-1, 1, date, 10); // invalid user ID
        accessExerciseLogs.insertExerciseLog(log2);
        System.out.println("Finished testInsertExerciseLogInvalidUserID");
    }

    @Test(expected = InvalidExerciseLogException.class)
    public void testInsertExerciseLogInvalidExerciseID() {
        System.out.println("\nStarting testInsertExerciseLogInvalidExerciseID");
        ExerciseLog log3 = new ExerciseLog(1, -1, date, 10); // invalid exercise ID
        accessExerciseLogs.insertExerciseLog(log3);
        System.out.println("Finished testInsertExerciseLogInvalidExerciseID");
    }

    @Test(expected = InvalidExerciseLogException.class)
    public void testInsertExerciseLogInvalidMins() {
        System.out.println("\nStarting testInsertExerciseLogInvalidMins");
        ExerciseLog log4 = new ExerciseLog(1, 1, date, -10); // invalid minutes
        accessExerciseLogs.insertExerciseLog(log4);
        System.out.println("Finished testInsertExerciseLogInvalidMins");
    }

    @Test(expected = InvalidExerciseLogException.class)
    public void testInsertDuplicateExerciseLog() {
        System.out.println("\nStarting testInsertDuplicateExerciseLog");
        ExerciseLog log5 = new ExerciseLog(0, 2, date, 7); // exercise log that already exists
        accessExerciseLogs.insertExerciseLog(log5);
        System.out.println("Finished testInsertDuplicateExerciseLog");
    }

    @Test
    public void testUpdateExerciseLog() {
        System.out.println("\nStarting testUpdateExerciseLog");
        ExerciseLog log1 = new ExerciseLog(1, 0, date, 10);

        accessExerciseLogs.updateExerciseLog(1, 0, date, log1);
        assertEquals(10, accessExerciseLogs.getExerciseLog(1, 0, date).getMinutes());

        // un-do the update
        accessExerciseLogs.updateExerciseLog(1, 0, date, new ExerciseLog(1, 0, date,5));
        assertEquals(5, accessExerciseLogs.getExerciseLog(1, 0, date).getMinutes());

        System.out.println("Finished testUpdateExerciseLog");
    }

    @Test(expected = InvalidExerciseLogException.class)
    public void testUpdateExerciseLogNull() {
        System.out.println("\nStarting testUpdateExerciseLogNull");
        accessExerciseLogs.updateExerciseLog(1, 0, date, null);
        System.out.println("Finished testUpdateExerciseLogNull");
    }

    @Test(expected = InvalidExerciseLogException.class)
    public void testUpdateExerciseLogInvalidUserID() {
        System.out.println("\nStarting testUpdateExerciseLogInvalidUserID");
        ExerciseLog log2 = new ExerciseLog(-1, 1, date, 10); // invalid user ID
        accessExerciseLogs.updateExerciseLog(1, 0, date, log2);
        System.out.println("Finished testUpdateExerciseLogInvalidUserID");
    }

    @Test(expected = InvalidExerciseLogException.class)
    public void testUpdateExerciseLogInvalidExerciseID() {
        System.out.println("\nStarting testUpdateExerciseLogInvalidExerciseID");
        ExerciseLog log3 = new ExerciseLog(1, -1, date, 10); // invalid exercise ID
        accessExerciseLogs.updateExerciseLog(1, 0, date, log3);
        System.out.println("Finished testUpdateExerciseLogInvalidExerciseID");
    }

    @Test(expected = InvalidExerciseLogException.class)
    public void testUpdateExerciseLogInvalidMins() {
        System.out.println("\nStarting testUpdateExerciseLogInvalidMins");
        ExerciseLog log4 = new ExerciseLog(1, 1, date, -10); // invalid exercise minutes
        accessExerciseLogs.updateExerciseLog(1, 0, date, log4);
        System.out.println("Finished testUpdateExerciseLogInvalidMins");
    }

    @Test(expected = ExerciseLogNotFoundException.class)
    public void testUpdateNotFoundExerciseLog() {
        System.out.println("\nStarting testUpdateNotFoundExerciseLog");
        ExerciseLog log1 = new ExerciseLog(1, 0, date, 10);
        accessExerciseLogs.updateExerciseLog(0, 3, date, log1); // not found
        System.out.println("Finished testUpdateNotFoundExerciseLog");
    }


    @Test
    public void testDeleteExerciseLog() {
        System.out.println("\nStarting testDeleteExerciseLog");

        accessExerciseLogs.deleteExerciseLog(0, 2, date);
        assertNull(accessExerciseLogs.getExerciseLog(0, 2, date));

        // add what we just deleted
        accessExerciseLogs.insertExerciseLog(new ExerciseLog(0, 2, date, 10));
        assertEquals(10, accessExerciseLogs.getExerciseLog(0, 2, date).getMinutes());
        assertEquals(1, accessExerciseLogs.getExerciseLogByUserDate(0, date).size());

        System.out.println("Finished testDeleteExerciseLog");
    }

    @Test(expected = ExerciseLogNotFoundException.class)
    public void testDeleteNotFoundExerciseLog1() {
        System.out.println("\nStarting testDeleteNotFoundExerciseLog1 - User ID and date are the same, but exerciseID is different.");
        accessExerciseLogs.deleteExerciseLog(0, 3, date); // not found
        System.out.println("Finished testDeleteNotFoundExerciseLog1");
    }

    @Test(expected = ExerciseLogNotFoundException.class)
    public void testDeleteNotFoundExerciseLog2() {
        System.out.println("\nStarting testDeleteNotFoundExerciseLog2 - User ID and exerciseID are the same, but date is different.");
        accessExerciseLogs.deleteExerciseLog(1, 1, date); // not found
        System.out.println("Finished testDeleteNotFoundExerciseLog2");
    }

    @Test
    public void testGetUserTotalDailyBurned() {
        System.out.println("\nStarting testGetUserTotalDailyBurned");
        MyDate date1 = new MyDate(new GregorianCalendar(2021, 0, 1));
        MyDate date2 = new MyDate(new GregorianCalendar(2021, 7, 19));

        int result = accessExerciseLogs.getUserTotalDailyBurned(2, date1);
        assertEquals(1600, result);

        result = accessExerciseLogs.getUserTotalDailyBurned(3, date1); // no logs
        assertEquals(0, result);
        result = accessExerciseLogs.getUserTotalDailyBurned(1, date2); // no logs
        assertEquals(0, result);

        System.out.println("Finished testGetUserTotalDailyBurned");
    }
}
