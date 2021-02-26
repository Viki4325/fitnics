package com.group12.fitnics.tests.business;

import com.group12.fitnics.business.AccessExerciseLogs;
import com.group12.fitnics.objects.ExerciseLog;
import com.group12.fitnics.objects.MyDate;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.List;

public class AccessExerciseLogsTest extends TestCase {

    private AccessExerciseLogs accessExerciseLogs;

    @Before
    public void setUp() {
        System.out.println("Starting test for AccessExerciseLogs");
        accessExerciseLogs = new AccessExerciseLogs();
    }

    @Test
    public void testGetExerciseLog() {
        System.out.println("\nStarting testGetExerciseLog");
        MyDate date = new MyDate(new GregorianCalendar(2021, 0, 1));
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
        MyDate date = new MyDate(new GregorianCalendar(2021, 0, 1));
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
        MyDate date = new MyDate(new GregorianCalendar(2021, 0, 1));
        ExerciseLog log1 = new ExerciseLog(0, 1, date, 10);
        ExerciseLog log2 = new ExerciseLog(-1, 1, date, 10); // invalid exercise log
        ExerciseLog log3 = new ExerciseLog(1, -1, date, 10); // invalid exercise log
        ExerciseLog log4 = new ExerciseLog(1, 1, date, -10); // invalid exercise log
        ExerciseLog log5 = new ExerciseLog(0, 2, date, 7); // exercise log that already exists

        String result = accessExerciseLogs.insertExerciseLog(log1);
        assertEquals(2, accessExerciseLogs.getExerciseLogByUserDate(0, date).size());
        assertEquals("Success", result);

        result = accessExerciseLogs.insertExerciseLog(log2);
        assertEquals("Fail", result);
        result = accessExerciseLogs.insertExerciseLog(log3);
        assertEquals("Fail", result);
        result = accessExerciseLogs.insertExerciseLog(log4);
        assertEquals("Fail", result);
        result = accessExerciseLogs.insertExerciseLog(log5);
        assertEquals("Fail", result);
        result = accessExerciseLogs.insertExerciseLog(null);
        assertEquals("Fail", result);

        // delete what we just inserted
        assertEquals("Success", accessExerciseLogs.deleteExerciseLog(0, 1, date));
        assertNull(accessExerciseLogs.getExerciseLog(0, 1, date));

        System.out.println("Finished testInsertExerciseLog");
    }

    @Test
    public void testDeleteExerciseLog() {
        System.out.println("\nStarting testDeleteExerciseLog");
        MyDate date = new MyDate(new GregorianCalendar(2021, 0, 1));

        String result = accessExerciseLogs.deleteExerciseLog(0, 2, date);
        assertNull(accessExerciseLogs.getExerciseLog(0, 2, date));
        assertEquals("Success", result);

        result = accessExerciseLogs.deleteExerciseLog(0, 3, date); // not found
        assertEquals("Fail", result);
        result = accessExerciseLogs.deleteExerciseLog(1, 1, date); // not found
        assertEquals("Fail", result);

        // add what we just deleted
        assertEquals("Success", accessExerciseLogs.insertExerciseLog(new ExerciseLog(0, 2, date, 10)));
        assertEquals(1, accessExerciseLogs.getExerciseLogByUserDate(0, date).size());

        System.out.println("Finished testDeleteExerciseLog");
    }

    @Test
    public void testUpdateExerciseLog() {
        System.out.println("\nStarting testUpdateExerciseLog");
        MyDate date = new MyDate(new GregorianCalendar(2021, 0, 1));
        ExerciseLog log1 = new ExerciseLog(1, 0, date, 10);
        ExerciseLog log2 = new ExerciseLog(-1, 1, date, 10); // invalid exercise log
        ExerciseLog log3 = new ExerciseLog(1, -1, date, 10); // invalid exercise log
        ExerciseLog log4 = new ExerciseLog(1, 1, date, -10); // invalid exercise log

        String result = accessExerciseLogs.updateExerciseLog(1, 0, date, log1);
        assertEquals("Success", result);
        assertEquals(10, accessExerciseLogs.getExerciseLog(1, 0, date).getMinutes());

        result = accessExerciseLogs.updateExerciseLog(0, 3, date, log1); // not found
        assertEquals("Fail", result);
        result = accessExerciseLogs.updateExerciseLog(1, 0, date, log2);
        assertEquals("Fail", result);
        result = accessExerciseLogs.updateExerciseLog(1, 0, date, log3);
        assertEquals("Fail", result);
        result = accessExerciseLogs.updateExerciseLog(1, 0, date, log4);
        assertEquals("Fail", result);

        // un-do the update
        result = accessExerciseLogs.updateExerciseLog(1, 0, date, new ExerciseLog(1, 0, date,5));
        assertEquals("Success", result);
        assertEquals(5, accessExerciseLogs.getExerciseLog(1, 0, date).getMinutes());

        System.out.println("Finished testUpdateExerciseLog");
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
