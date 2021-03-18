package com.group12.fitnics.exceptions;

public class InvalidUserName extends InvalidUserInfo {
    public InvalidUserName(String error) {
        super("The username does not meet the requirement: \n"+error);

    }
}
