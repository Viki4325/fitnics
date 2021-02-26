package com.group12.fitnics.tests.business;

import com.group12.fitnics.business.AccessUsers;
import com.group12.fitnics.objects.User;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AccessUsersTest extends TestCase {

    private AccessUsers accessUsers;

    @Before
    public void setUp() {
        System.out.println("Starting test for AccessUsers");
        accessUsers = new AccessUsers();
    }

    @Test
    public void testGetUsers() {
        System.out.println("\nStarting testGetUsers");
        List<User> users = accessUsers.getUsers();

        assertEquals(4, users.size());

        System.out.println("Finished testGetUsers");
    }

    @Test
    public void testGetUserById() {
        System.out.println("\nStarting testGetUserById");
        User alice = accessUsers.getUserById(0);

        assertNotNull(alice);
        assertEquals(0, alice.getUserID());
        assertEquals("alice", alice.getUsername());

        User nobody = accessUsers.getUserById(7);
        assertNull(nobody);

        System.out.println("Finished testGetUserById");
    }

    @Test
    public void testGetUserByName() {
        System.out.println("\nStarting testGetUserByName");
        User alice = accessUsers.getUserByName("alice");

        assertNotNull(alice);
        assertEquals(0, alice.getUserID());
        assertEquals("alice", alice.getUsername());

        User nobody = accessUsers.getUserByName("adam");
        assertNull(nobody);

        System.out.println("Finished testGetUserByName");
    }

    @Test
    public void testInsertUser() {
        System.out.println("\nStarting testInsertUser");
        User eve = new User("eve", 1, 47, 160, 'F');
        String result = accessUsers.insertUser(eve);

        assertEquals("Success", result);
        assertEquals(4, eve.getUserID());
        assertEquals("eve", accessUsers.getUserByName("eve").getUsername());
        assertEquals(1, accessUsers.getUserByName("eve").getActivityLevel());
        assertEquals(47.0, accessUsers.getUserByName("eve").getWeight(), 0.0001);
        assertEquals(160.0, accessUsers.getUserByName("eve").getHeight(), 0.0001);
        assertEquals('F', accessUsers.getUserByName("eve").getGender());

        User alice2 = new User("alice", 0, 50, 165, 'F');
        result = accessUsers.insertUser(alice2); // A user with existing name can not be inserted
        assertEquals("Fail", result);
        result = accessUsers.insertUser(null);
        assertEquals("Fail", result);

        // add what we just deleted
        assertEquals("Success", accessUsers.deleteUser(4));
        assertNull(accessUsers.getUserByName("eve"));

        System.out.println("Finished testInsertUser");
    }

    @Test
    public void testUpdateUser() {
        System.out.println("\nStarting testUpdateUser");
        User updatedAlice = new User("alice", 2, 49, 163, 'F');
        String result = accessUsers.updateUser(0, updatedAlice);

        assertEquals("Success", result);
        User alice = accessUsers.getUserByName("alice");
        assertEquals(2, alice.getActivityLevel());
        assertEquals(49, alice.getWeight(), 0.0001);
        assertEquals(163, alice.getHeight(), 0.0001);

        result = accessUsers.updateUser(7, updatedAlice); // not found
        assertEquals("Fail", result);
        result = accessUsers.updateUser(0, null);
        assertEquals("Fail", result);

        // un-do the update
        result = accessUsers.updateUser(0, new User("alice", 1, 55, 163, 'F'));
        assertEquals("Success", result);

        System.out.println("Finished testUpdateUser");
    }

    @Test
    public void testDeleteUser() {
        System.out.println("\nStarting testDeleteUser");

        String result = accessUsers.deleteUser(3);
        assertEquals("Success", result);
        assertEquals(3, accessUsers.getUsers().size());

        result = accessUsers.deleteUser(7); // not found
        assertEquals("Fail", result);

        // add what we just deleted
        User david = new User("david", 1, 50, 160, 'M');
        david.setUserID(3);
        assertEquals("Success", accessUsers.insertUser(david));
        assertEquals(4, accessUsers.getUsers().size());

        System.out.println("Finished testDeleteUser");
    }
}
