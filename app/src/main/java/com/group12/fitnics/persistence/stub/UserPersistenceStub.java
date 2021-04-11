package com.group12.fitnics.persistence.stub;

import com.group12.fitnics.objects.User.User;
import com.group12.fitnics.persistence.IUserPersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserPersistenceStub implements IUserPersistence {
    private List<User> users;

    public UserPersistenceStub() {
        this.users = new ArrayList<>();
        int[] units = {1, 0};

        User alice = new User("alice", 15, 4, 1998, 1, 55, 163, 'F', 0, units);
        User bob = new User("bob", 1, 5, 2000, 2, 70, 175, 'M', 1, units);
        User carol = new User("carol", 22, 7, 1995, 3, 47, 160, 'F', 2, units);
        User david = new User("david", 27, 11, 1985, 1, 50, 160, 'M', 0, units);
        alice.setUserID(0);
        bob.setUserID(1);
        carol.setUserID(2);
        david.setUserID(3);
        User.setLastUserID(3);
        users.add(alice);
        users.add(bob);
        users.add(carol);
        users.add(david);
    }

    @Override
    public List<User> getUserSequential() {
        return Collections.unmodifiableList(users);
    }

    // get a user by userID
    @Override
    public User getUserByID(int userID) {
        User result = null;
        for(int i = 0; i < users.size() && result == null; i++) {
            if(users.get(i).getUserID() == userID)
                result = users.get(i);
        }
        return result;
    }

    @Override
    public User getUserByUsername(String username) {
        User result = null;
        for(int i = 0; i < users.size() && result == null; i++) {
            if(users.get(i).getUsername().equals(username))
                result = users.get(i);
        }
        return result;
    }

    @Override
    public int insertUser(User currentUser) {
        currentUser.setUserID();
        users.add(currentUser);

        return currentUser.getUserID();
    }

    // update a user with userID to the updatedUser
    @Override
    public void updateUser(int userID, User updatedUser) {
        boolean found = false;
        for(int i = 0; i < users.size() && !found; i++) {
            if(users.get(i).getUserID() == userID) {
                User prevUser = users.get(i);
                updatedUser.setUserID(prevUser.getUserID());
                updatedUser.setDailyCaloricNeeds();
                users.set(i, updatedUser);
                found = true;
            }
        }
    }

    // delete a user by userID
    @Override
    public void deleteUser(int userID) {
        boolean found = false;
        for(int i = 0; i < users.size() && !found; i++) {
            if(users.get(i).getUserID() == userID) {
                users.remove(i);
                found = true;
            }
        }
    }
}
