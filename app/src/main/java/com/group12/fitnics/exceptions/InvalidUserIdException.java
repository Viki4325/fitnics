package com.group12.fitnics.exceptions;

public class InvalidUserIdException extends InvalidUserException {

    public InvalidUserIdException(String error) {
        super("The user Id is invalid:\n"+error);
    }

}
