package com.group12.fitnics.persistence;

import com.group12.fitnics.objects.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserPersistenceStub implements IUserPersistence {
    private List<User> users;

    public UserPersistenceStub() {
        this.users = new ArrayList<>();

        User alice = new User("alice", 1, 55, 163, 'F');
        User bob = new User("bob", 2, 70, 175, 'M');
        User carol = new User("carol", 3, 47, 160, 'F');
        User david = new User("david", 1, 50, 160, 'M');
        alice.setUserID(0); alice.setBirthYear(1998); alice.setBirthMonth(4); alice.setBirthDay(15); alice.setGoal(0);
        bob.setUserID(1);
        carol.setUserID(2);
        david.setUserID(3);
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
    public String insertUser(User currentUser) {
        if (currentUser == null)
            return "Fail";

        // if there exists same username already, do not allow it to be inserted
        if (getUserByUsername(currentUser.getUsername()) != null)
            return "Fail";

        currentUser.setUserID();
        users.add(currentUser);
        return "Success";
    }

    // update a user with userID to the updatedUser
    @Override
    public String updateUser(int userID, User updatedUser) {
        if (updatedUser == null)
            return "Fail";

        boolean found = false;
        for(int i = 0; i < users.size() && !found; i++) {
            if(users.get(i).getUserID() == userID) {
                User prevUser = users.get(i);
                updatedUser.setUserID(prevUser.getUserID());
                users.set(i, updatedUser);
                found = true;
            }
        }
        if (!found)
            return "Fail";

        return "Success";
    }

    // delete a user by userID
    @Override
    public String deleteUser(int userID) {
        boolean found = false;
        for(int i = 0; i < users.size() && !found; i++) {
            if(users.get(i).getUserID() == userID) {
                users.remove(i);
                found = true;
            }
        }
        if (!found)
            return "Fail";

        return "Success";
    }
}
