package com.group12.fitnics.business;

import com.group12.fitnics.application.Services;
import com.group12.fitnics.exceptions.InvalidUserException;
import com.group12.fitnics.exceptions.InvalidUserNameException;
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

    public int insertUser(User currentUser) throws InvalidUserException, InvalidUserNameException {
        if (currentUser == null)
            throw new InvalidUserException("The user is not valid. ");
        // if there exists same username already, do not allow it to be inserted
        if (userPersistence.getUserByUsername(currentUser.getUsername()) != null)
            throw new InvalidUserNameException("There exists duplicate username. ");
        if (currentUser.getUsername().length() > 20)
            throw new InvalidUserNameException("The username is too long, should be no more than 20 characters.");

        return userPersistence.insertUser(currentUser);
    }

    public void updateUser(int userID, User currentUser) throws InvalidUserException, UserNotFoundException {
        if (currentUser == null)
            throw new InvalidUserException("The user is not valid. ");

        if (userPersistence.getUserByID(userID) == null)
            throw new UserNotFoundException("There's no user with the userID. ");

        currentUser.setDailyCaloricNeeds();
        userPersistence.updateUser(userID, currentUser);
    }

    public void deleteUser(int userID) throws UserNotFoundException{
        if (userPersistence.getUserByID(userID) == null)
            throw new UserNotFoundException("There's no user with the userID. ");

        userPersistence.deleteUser(userID);
    }

}
