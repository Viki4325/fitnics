package com.group12.fitnics.exceptions;

public class InvalidUserInfo extends RuntimeException{

    public InvalidUserInfo (String error) {
        super("User information is invalid: \n" + error);
    }

}
