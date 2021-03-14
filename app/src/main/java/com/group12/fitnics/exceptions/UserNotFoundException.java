package com.group12.fitnics.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String error) {
        super("The user is not found:\n" + error);
    }

}
