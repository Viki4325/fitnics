package com.group12.fitnics.business;

import com.group12.fitnics.application.Services;
import com.group12.fitnics.exceptions.InvalidUserException;
import com.group12.fitnics.exceptions.UserNotFoundException;
import com.group12.fitnics.objects.User;
import com.group12.fitnics.persistence.IUserPersistence;

import java.util.List;

public class AccessUsers {

    private IUserPersistence userPersistence;

    public AccessUsers(IUserPersistence userPersistence) {
        this.userPersistence = userPersistence;
    }

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

    public void insertUser(User currentUser) throws InvalidUserException {
        userPersistence.insertUser(currentUser);
    }

    public void updateUser(int userID, User currentUser) throws InvalidUserException, UserNotFoundException {
        userPersistence.updateUser(userID, currentUser);
    }

    public void deleteUser(int userID) throws UserNotFoundException{
        userPersistence.deleteUser(userID);
    }

}
