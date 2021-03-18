package com.group12.fitnics.exceptions;

public class InvalidSignUpDate extends InvalidUserInfo{
    public InvalidSignUpDate(String error) {
        super("The date is not invalid. Out of range: \n"+error);
    }
}
