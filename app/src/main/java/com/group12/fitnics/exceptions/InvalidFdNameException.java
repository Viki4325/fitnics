package com.group12.fitnics.exceptions;

public class InvalidFdNameException extends InvalidFoodException {

    public InvalidFdNameException(String error) {
        super("The food name is invalid: \n" + error);
    }

}
