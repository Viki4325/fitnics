package com.group12.fitnics.exceptions;

public class InvalidUserException extends RuntimeException{

    public InvalidUserException (String error) {
        super("Unable to access User data:\n" + error);
    }

}
