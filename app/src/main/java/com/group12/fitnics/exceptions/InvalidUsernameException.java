package com.group12.fitnics.exceptions;

public class InvalidUsernameException extends InvalidUserException{

    public InvalidUsernameException(String error) {
        super("The username is invalid: \n" + error);
    }

}
