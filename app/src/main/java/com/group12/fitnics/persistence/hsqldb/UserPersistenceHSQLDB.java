package com.group12.fitnics.persistence.hsqldb;

import com.group12.fitnics.objects.User;
import com.group12.fitnics.persistence.IUserPersistence;

import java.util.List;

public class UserPersistenceHSQLDB implements IUserPersistence {
    @Override
    public List<User> getUserSequential() {
        return null;
    }

    @Override
    public User getUserByID(int userID) {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public void insertUser(User currentUser) {

    }

    @Override
    public void updateUser(int userID, User currentUser) {

    }

    @Override
    public void deleteUser(int userID) {

    }
}
