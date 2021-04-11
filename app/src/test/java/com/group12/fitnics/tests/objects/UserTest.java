package com.group12.fitnics.tests.objects;

import com.group12.fitnics.business.AccessUsers;
import com.group12.fitnics.objects.User.User;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserTest {

    private final int[] units = {1, 0};

    @Test
    public void testUserCreation() {
        System.out.println("\nStarting testUserCreation");
        User eve = new User("eve", 15, 4, 1998, 1, 47, 160, 'F', 1, units);

        assertNotNull(eve);
        assertEquals("eve", eve.getUsername());
        assertEquals(1, eve.getActivityLevel());
        assertEquals(47, eve.getWeight(), 0.0001);
        assertEquals(160, eve.getHeight(), 0.0001);
        assertEquals('F', eve.getGender());
        assertEquals(1, eve.getGoal());
        assertEquals(2333.83, eve.getDailyCaloricNeeds(),0.01);

        User frank = new User("frank", 15, 8, 2000, 2, 66, 174, 'M', 1, units);

        assertNotNull(frank);
        assertEquals("frank", frank.getUsername());
        assertEquals(2, frank.getActivityLevel());
        assertEquals(66, frank.getWeight(), 0.0001);
        assertEquals(174, frank.getHeight(), 0.0001);
        assertEquals('M', frank.getGender());
        assertEquals(1, frank.getGoal());
        assertEquals(4468.44, frank.getDailyCaloricNeeds(),0.01);

        System.out.println("Finished testUserCreation");
    }

    @Test
    public void testUserGetters() {
        System.out.println("\nStarting testUserCreation");
        AccessUsers accessUsers = new AccessUsers();
        User alice = accessUsers.getUserByName("alice");

        assertNotNull(alice);
        assertEquals(0, alice.getUserID());
        assertEquals("alice", alice.getUsername());
        assertEquals(1, alice.getActivityLevel());
        assertEquals(55, alice.getWeight(), 0.0001);
        assertEquals(163, alice.getHeight(), 0.0001);
        assertEquals(1998, alice.getBirthYear());
        assertEquals(4, alice.getBirthMonth());
        assertEquals(15, alice.getBirthDay());
        assertEquals(0, alice.getGoal());
        assertEquals('F', alice.getGender());
        assertEquals(1659.01, alice.getDailyCaloricNeeds(),0.001);

        System.out.println("Finished testUserCreation");
    }

    @Test
    public void testUserSetters() {
        System.out.println("\nStarting testUserSetters");
        User eve = new User("eve", 15, 4, 1998, 1, 47, 160, 'F', 1, units);

        eve.setActivityLevel(2);
        eve.setUserID(7);
        eve.setUsername("eve77");
        eve.setGender('O');
        eve.setGoal(2);
        eve.setWeight(45);
        eve.setHeight(161);
        eve.setBirthYear(2000);
        eve.setBirthMonth(2);
        eve.setBirthDay(14);
        eve.setDailyCaloricNeeds(2400);

        assertNotNull(eve);
        assertEquals(7, eve.getUserID());
        assertEquals("eve77", eve.getUsername());
        assertEquals(2, eve.getActivityLevel());
        assertEquals(45, eve.getWeight(), 0.0001);
        assertEquals(161, eve.getHeight(), 0.0001);
        assertEquals(2000, eve.getBirthYear());
        assertEquals(2, eve.getBirthMonth());
        assertEquals(14, eve.getBirthDay());
        assertEquals(2, eve.getGoal());
        assertEquals('O', eve.getGender());
        assertEquals(2400, (int)eve.getDailyCaloricNeeds());

        System.out.println("Finished testUserSetters");
    }
}
