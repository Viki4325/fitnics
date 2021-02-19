package com.group12.fitnics.application;

import com.group12.fitnics.persistence.UserPersistence;
import com.group12.fitnics.persistence.UserPersistenceStub;

public class Services {

    private static UserPersistence userPersistence = null;

    public static UserPersistence getUserPersistence() {
        if (userPersistence == null) {
            userPersistence = new UserPersistenceStub();
        }
        return userPersistence;
    }

}