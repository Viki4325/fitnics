package com.group12.fitnics.tests.objects;

import com.group12.fitnics.business.AccessUsers;
import com.group12.fitnics.enums.ActivityLevel;
import com.group12.fitnics.enums.Gender;
import com.group12.fitnics.enums.Goal;
import com.group12.fitnics.objects.User;
import com.group12.fitnics.persistence.stub.UserPersistenceStub;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserTest {

    private final int[] units = {1, 0};

    @Test
    public void testUserCreation() {
        System.out.println("\nStarting testUserCreation");
        User eve = new User("eve", 15, 4, 1998, 1, 47, 160, 'F', 0, units);

        assertNotNull(eve); // age: 23
        assertEquals("eve", eve.getUsername());
        assertEquals(1, eve.getActivityLevel().getValue());
        assertEquals(47, eve.getWeight(), 0.0001);
        assertEquals(160, eve.getHeight(), 0.0001);
        assertEquals('F', eve.getGender().getValue());
        assertEquals(0, eve.getGoal().getValue());
        assertEquals(3076.55, eve.getDailyCaloricNeeds(),0.01);

        User frank = new User("frank", 15, 8, 2000, 2, 66, 174, 'M', 0, units);

        assertNotNull(frank); // age: 20
        assertEquals("frank", frank.getUsername());
        assertEquals(2, frank.getActivityLevel().getValue());
        assertEquals(66, frank.getWeight(), 0.0001);
        assertEquals(174, frank.getHeight(), 0.0001);
        assertEquals('M', frank.getGender().getValue());
        assertEquals(0, frank.getGoal().getValue());
        assertEquals(5218.44, frank.getDailyCaloricNeeds(),0.01);

        System.out.println("Finished testUserCreation");
    }

    @Test
    public void testUserGetters() {
        System.out.println("\nStarting testUserCreation");
        AccessUsers accessUsers = new AccessUsers(new UserPersistenceStub());
        User alice = accessUsers.getUserByName("alice");

        assertNotNull(alice);
        assertEquals(0, alice.getUserID());
        assertEquals("alice", alice.getUsername());
        assertEquals(1, alice.getActivityLevel().getValue());
        assertEquals(55, alice.getWeight(), 0.0001);
        assertEquals(163, alice.getHeight(), 0.0001);
        assertEquals(1998, alice.getBirthYear());
        assertEquals(4, alice.getBirthMonth());
        assertEquals(15, alice.getBirthDay());
        assertEquals(0, alice.getGoal().getValue());
        assertEquals('F', alice.getGender().getValue());
        assertEquals(3151.725, alice.getDailyCaloricNeeds(),0.001);

        System.out.println("Finished testUserCreation");
    }

    @Test
    public void testUserSetters() {
        System.out.println("\nStarting testUserSetters");
        User eve = new User("eve", 15, 4, 1998, 1, 47, 160, 'F', 1, units);

        //before changing
        assertEquals(eve.getActivityLevel(), ActivityLevel.SOMEWHAT_ACTIVE);

        eve.setActivityLevel(ActivityLevel.ACTIVE);
        eve.setUserID(7);
        eve.setUsername("eve77");
        eve.setGender(Gender.OTHER);
        eve.setGoal(Goal.GOAL_MAINTAIN);
        eve.setWeight(45);
        eve.setHeight(161);
        eve.setBirthYear(2000);
        eve.setBirthMonth(2);
        eve.setBirthDay(14);
        eve.setDailyCaloricNeeds(2400);

        assertNotNull(eve);
        assertEquals(7, eve.getUserID());
        assertEquals("eve77", eve.getUsername());
        assertEquals(2, eve.getActivityLevel().getValue());
        assertEquals(45, eve.getWeight(), 0.0001);
        assertEquals(161, eve.getHeight(), 0.0001);
        assertEquals(2000, eve.getBirthYear());
        assertEquals(2, eve.getBirthMonth());
        assertEquals(14, eve.getBirthDay());
        assertEquals(2, eve.getGoal().getValue());
        assertEquals('O', eve.getGender().getValue());
        assertEquals(2400, (int)eve.getDailyCaloricNeeds());

        System.out.println("Finished testUserSetters");
    }
}
