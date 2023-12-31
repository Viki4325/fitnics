package com.group12.fitnics.tests.business;

import com.group12.fitnics.business.AccessUsers;
import com.group12.fitnics.exceptions.InvalidUserException;
import com.group12.fitnics.exceptions.InvalidUsernameException;
import com.group12.fitnics.exceptions.UserNotFoundException;
import com.group12.fitnics.objects.User;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

public class AccessUsersTest {

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
        accessUsers.insertUser(eve);

        assertNotNull(accessUsers.getUserByName("eve"));
        assertEquals(4, eve.getUserID());
        assertEquals("eve", accessUsers.getUserByName("eve").getUsername());
        assertEquals(1, accessUsers.getUserByName("eve").getActivityLevel());
        assertEquals(47.0, accessUsers.getUserByName("eve").getWeight(), 0.0001);
        assertEquals(160.0, accessUsers.getUserByName("eve").getHeight(), 0.0001);
        assertEquals('F', accessUsers.getUserByName("eve").getGender());

        // delete what we just added
        accessUsers.deleteUser(4);
        assertNull(accessUsers.getUserByName("eve"));

        System.out.println("Finished testInsertUser");
    }

    @Test(expected = InvalidUsernameException.class)
    public void testInsertDuplicateUser() {
        System.out.println("\nStarting testInsertDuplicateUser");
        // A user with existing name can not be inserted
        User alice2 = new User("alice", 0, 50, 165, 'F');
        accessUsers.insertUser(alice2);
        System.out.println("Finished InvalidUsernameException");
    }

    @Test(expected = InvalidUserException.class)
    public void testInsertUserNull() {
        System.out.println("\nStarting testInsertUserNull");
        accessUsers.insertUser(null);
        System.out.println("Finished testInsertUserNull");
    }

    @Test
    public void testUpdateUser() {
        System.out.println("\nStarting testUpdateUser");
        User updatedAlice = new User("alice", 2, 49, 163, 'F');
        accessUsers.updateUser(0, updatedAlice);

        User alice = accessUsers.getUserByName("alice");
        assertEquals(2, alice.getActivityLevel());
        assertEquals(49, alice.getWeight(), 0.0001);
        assertEquals(163, alice.getHeight(), 0.0001);

        // un-do the update
        accessUsers.updateUser(0, new User("alice", 1, 55, 163, 'F'));

        System.out.println("Finished testUpdateUser");
    }

    @Test(expected = InvalidUserException.class)
    public void testUpdateInvalidUser() {
        System.out.println("\nStarting testUpdateInvalidUser");
        accessUsers.updateUser(0, null);
        System.out.println("Finished testUpdateInvalidUser");
    }

    @Test(expected = UserNotFoundException.class)
    public void testUpdateNotFoundUser() {
        System.out.println("\nStarting testUpdateNotFoundUser");
        User updatedAlice = new User("alice", 2, 49, 163, 'F');
        accessUsers.updateUser(7, updatedAlice); // not found
        System.out.println("Finished testUpdateNotFoundUser");
    }

    @Test
    public void testDeleteUser() {
        System.out.println("\nStarting testDeleteUser");

        accessUsers.deleteUser(3);
        assertNull(accessUsers.getUserById(3));
        assertEquals(3, accessUsers.getUsers().size());

        // add what we just deleted
        User david = new User("david", 1, 50, 160, 'M');
        accessUsers.insertUser(david);
        david.setUserID(3);
        assertNotNull(accessUsers.getUserById(3));
        assertNotNull(accessUsers.getUserByName("david"));
        assertEquals(4, accessUsers.getUsers().size());

        System.out.println("Finished testDeleteUser");
    }

    @Test(expected = UserNotFoundException.class)
    public void testDeleteNotFoundUser() {
        System.out.println("\nStarting testDeleteNotFoundUser");
        accessUsers.deleteUser(7); // not found
        System.out.println("Finished testDeleteNotFoundUser");
    }
}
