package com.group12.fitnics.exceptions;

public class InvalidSignUpDateException extends InvalidUserInfoException {
    public InvalidSignUpDateException(String error) {
        super("The date is not invalid. Out of range: \n"+error);
    }
}
