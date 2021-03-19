package com.group12.fitnics.tests.business;

import com.group12.fitnics.business.UnitConverter;
import com.group12.fitnics.exceptions.InvalidUnitsException;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitConverterTest {

    @Before
    public void setUp() {
        System.out.println("Starting test for UnitConverter");

    }

    @Test
    public void testConvertWeight() {
        System.out.println("\nStarting testConvertWeight");
        assertEquals(101.43, UnitConverter.KGToLB(46), 0.1);
        assertEquals(46, UnitConverter.LBToKg(101.43), 0.1);
        assertEquals(55.33, UnitConverter.LBToKg(122), 0.1);
        assertEquals(122, UnitConverter.KGToLB(55.33), 0.1);
        System.out.println("Finished testConvertWeight");
    }

    @Test
    public void testConvertHeight() {
        System.out.println("\nStarting testConvertHeight");
        assertEquals(152.4, UnitConverter.FTToCM(5), 0.1);
        assertEquals(5, UnitConverter.CMToFT(152.4), 0.1);
        assertEquals(5.74, UnitConverter.CMToFT(175), 0.1);
        assertEquals(175, UnitConverter.FTToCM(5.74), 0.1);
        System.out.println("Finished testConvertHeight");
    }

    @Test
    public void testConvertUnitToString() {
        System.out.println("\nStarting testConvertUnitToString");
        assertEquals("10.05", UnitConverter.convertUnitToString(10.05432, 2));
        assertEquals("10.06", UnitConverter.convertUnitToString(10.05789, 2));
        System.out.println("Finished testConvertUnitToString");
    }

    @Test(expected = InvalidUnitsException.class)
    public void testInvalidUnitsException() {
        System.out.println("\nStarting testInvalidUnitsException");
        UnitConverter.convertUnitToString(0.0, 4);
        System.out.println("Finished testInvalidUnitsException");
    }

    @Test
    public void testCalculatePercent() {
        System.out.println("\nStarting testCalculatePercent");
        assertEquals(96, UnitConverter.calculatePercent(1024.0, 45.0));
        System.out.println("Finished testCalculatePercent");
    }
}
