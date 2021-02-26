package com.group12.fitnics.persistence;

import com.group12.fitnics.objects.User;

import java.util.ArrayList;
import java.util.List;

public interface IUserPersistence {
    List<User> getUserSequential();

    User getUserByID(final int userID);

    User getUserByUsername(final String username);

    String insertUser(final User currentUser);

    String updateUser(final int userID, final User currentUser);

    String deleteUser(final int userID);

}
