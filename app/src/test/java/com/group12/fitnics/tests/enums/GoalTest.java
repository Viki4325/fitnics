package com.group12.fitnics.tests.enums;
import com.group12.fitnics.enums.ActivityLevel;
import com.group12.fitnics.enums.Gender;
import com.group12.fitnics.enums.Goal;

import org.junit.Test;

import java.lang.reflect.GenericDeclaration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GoalTest {

    @Test
    public void testGetValueOf(){
        assertEquals(Goal.NO_GOAL,Goal.valueOf(-1));
        assertEquals(Goal.GOAL_GAIN,Goal.valueOf(0));
        assertEquals(Goal.GOAL_LOOSE,Goal.valueOf(1));
        assertEquals(Goal.GOAL_MAINTAIN,Goal.valueOf(2));
    }

    @Test
    public void testGetValue(){
        assertEquals(Goal.NO_GOAL.getValue(),-1);
        assertEquals(Goal.GOAL_LOOSE.getValue(),1);
        assertEquals(Goal.GOAL_MAINTAIN.getValue(),2);
        assertEquals(Goal.GOAL_GAIN.getValue(),0);
    }

    @Test
    public void testGetAddingTerm(){
        assertEquals(Goal.NO_GOAL.getAddingTerm(),0,0.1);
        assertEquals(Goal.GOAL_LOOSE.getAddingTerm(),-750,0.1);
        assertEquals(Goal.GOAL_MAINTAIN.getAddingTerm(),0,0.1);
        assertEquals(Goal.GOAL_GAIN.getAddingTerm(),750,0.1);
    }
}
