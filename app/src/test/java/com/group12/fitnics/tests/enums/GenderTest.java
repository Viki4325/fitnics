package com.group12.fitnics.tests.enums;
import com.group12.fitnics.enums.Gender;

import org.junit.Test;

import java.lang.reflect.GenericDeclaration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GenderTest {

    @Test
    public void testGetValueOf_char(){
       assertEquals(Gender.MALE,Gender.valueOf('M'));
       assertEquals(Gender.FEMALE,Gender.valueOf('F'));
       assertEquals(Gender.OTHER,Gender.valueOf('O'));
    }

    @Test
    public void testGetValueOf_int(){
        assertEquals(0,Gender.MALE.getPos());
        assertEquals(1,Gender.FEMALE.getPos());
        assertEquals(2,Gender.OTHER.getPos());
    }

    @Test
    public void testAddingTerm(){
        assertEquals(66,Gender.MALE.getAddingTerm());
        assertEquals(655,Gender.FEMALE.getAddingTerm());
        assertEquals(66,Gender.OTHER.getAddingTerm());
    }

    @Test
    public void testHeightFactor(){
        assertEquals(12.9,Gender.MALE.getHeightFactor(),0.001);
        assertEquals(4.7,Gender.FEMALE.getHeightFactor(),0.001);
        assertEquals(12.9, Gender.OTHER.getHeightFactor(),0.001);
    }

    @Test
    public void testWeightFactor(){
        assertEquals(6.3,Gender.MALE.getWeightFactor(),0.001);
        assertEquals(4.3,Gender.FEMALE.getWeightFactor(),0.001);
        assertEquals(6.3,Gender.OTHER.getWeightFactor(),0.001);
    }

    @Test
    public void testAgeFactor(){
        assertEquals(6.8,Gender.MALE.getAgeFactor(),0.001);
        assertEquals(4.7,Gender.FEMALE.getAgeFactor(),0.001);
        assertEquals(6.8,Gender.OTHER.getAgeFactor(),0.001);
    }
}
