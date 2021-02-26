package com.group12.fitnics.business;

import com.group12.fitnics.application.Services;
import com.group12.fitnics.objects.User;
import com.group12.fitnics.persistence.IUserPersistence;

import java.util.List;

public class AccessUsers {

    private IUserPersistence userPersistence;

    public AccessUsers() {
        userPersistence = Services.getUserPersistence();
    }

    public List<User> getUsers() {
        return userPersistence.getUserSequential();
    }

    public User getUserById(int id) {
        return userPersistence.getUserByID(id);
    }

    public User getUserByName(String username) {
        return userPersistence.getUserByUsername(username);
    }

    public String insertUser(User currentUser) {
        return userPersistence.insertUser(currentUser);
    }

    public String updateUser(int userID, User currentUser) {
        return userPersistence.updateUser(userID, currentUser);
    }

    public String deleteUser(int userID) {
        return userPersistence.deleteUser(userID);
    }

}
