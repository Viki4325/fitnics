package com.group12.fitnics.tests.business;

import com.group12.fitnics.business.DateHelper;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class DateHelperTest {

    String str;
    LocalDate date;

    @Before
    public void setUp() {
        System.out.println("Starting test for DateHelper");
        str = "1999-09-29";
        date = LocalDate.of(1999, 9, 29);
    }

    @Test
    public void testStringToDate() {
        System.out.println("\nStarting testStringToDate");

        assertEquals(date, DateHelper.stringToDate(str));

        System.out.println("Finished testStringToDate");
    }

    @Test
    public void testDateToString() {
        System.out.println("\nStarting testDateToString");

        assertEquals(str, DateHelper.dateToString(date));

        System.out.println("Finished testDateToString");
    }

}
