package com.group12.fitnics.tests.objects;

import com.group12.fitnics.objects.MyDate;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MyDateTest {

    @Test
    public void testMyDateCreation() {
        System.out.println("\nStarting testMyDateCreation");
        Calendar dateInfo = new GregorianCalendar(2021, 1, 15);
        MyDate date = new MyDate(dateInfo);

        assertNotNull(date);
        assertEquals(2021, date.getYear());
        assertEquals(1, date.getMonth());
        assertEquals(15, date.getDate());
        assertEquals(dateInfo, date.getDateInfo());

        System.out.println("Finished testMyDateCreation");
    }

    @Test
    public void testMyDateEquals() {
        System.out.println("\nStarting testMyDateEquals");
        MyDate date1 = new MyDate(new GregorianCalendar(2021, 1, 1));
        MyDate date2 = new MyDate(new GregorianCalendar(2021, 1, 1));
        MyDate date3 = new MyDate(new GregorianCalendar(2021, 1, 2));

        assertTrue(date1.equals(date2));
        assertFalse(date1.equals(date3));

        System.out.println("Finished testMyDateEquals");
    }

    @Test
    public void testMyDateSetter() {
        System.out.println("\nStarting testMyDateSetter");
        MyDate date = new MyDate(new GregorianCalendar(2021, 1, 1));
        Calendar newDateInfo = new GregorianCalendar(2022, 12, 7);

        date.setDateInfo(newDateInfo);
        assertEquals(2021, date.getYear());
        assertEquals(12, date.getMonth());
        assertEquals(7, date.getDate());
        assertEquals(newDateInfo, date.getDateInfo());

        System.out.println("Finished testMyDateSetter");
    }

    @Test
    public void testMyDateToString() {
        System.out.println("\nStarting testMyDateToString");
        Calendar dateInfo = new GregorianCalendar(2021, 1, 15);
        MyDate date = new MyDate(dateInfo);

        assertEquals("2021-01-15", date.toString());

        System.out.println("Finished testMyDateToString");
    }

}
