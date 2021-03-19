package com.group12.fitnics.exceptions;

public class InvalidUserNameException extends InvalidUserInfoException {
    public InvalidUserNameException(String error) {
        super("The username does not meet the requirement: \n"+error);

    }
}
