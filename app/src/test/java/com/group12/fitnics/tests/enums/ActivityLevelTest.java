package com.group12.fitnics.tests.enums;
import com.group12.fitnics.enums.ActivityLevel;

import org.junit.Test;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ActivityLevelTest {

    @Test
    public void testGetValueOf(){
        assertEquals(ActivityLevel.NOT_ACTIVE,ActivityLevel.valueOf(0));
        assertEquals(ActivityLevel.SOMEWHAT_ACTIVE,ActivityLevel.valueOf(1));
        assertEquals(ActivityLevel.ACTIVE,ActivityLevel.valueOf(2));
        assertEquals(ActivityLevel.VERY_ACTIVE,ActivityLevel.valueOf(3));

    }

    @Test
    public void testGetValue(){
        assertEquals(ActivityLevel.NOT_ACTIVE.getValue(),0);
        assertEquals(ActivityLevel.SOMEWHAT_ACTIVE.getValue(),1);
        assertEquals(ActivityLevel.ACTIVE.getValue(),2);
        assertEquals(ActivityLevel.VERY_ACTIVE.getValue(),3);
    }

    @Test
    public void testGetHBfactor(){
        assertEquals(1.2, ActivityLevel.NOT_ACTIVE.getHBFactor(), 0.0);
        assertEquals(1.55, ActivityLevel.SOMEWHAT_ACTIVE.getHBFactor(), 0.0);
        assertEquals(1.725, ActivityLevel.ACTIVE.getHBFactor(), 0.0);
        assertEquals(1.9, ActivityLevel.VERY_ACTIVE.getHBFactor(), 0.0);
    }
}
