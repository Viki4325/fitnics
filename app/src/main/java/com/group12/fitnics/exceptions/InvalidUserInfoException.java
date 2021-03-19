package com.group12.fitnics.exceptions;

public class InvalidUserInfoException extends RuntimeException{

    public InvalidUserInfoException(String error) {
        super("User information is invalid: \n" + error);
    }

}
