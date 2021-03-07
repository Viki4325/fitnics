package com.group12.fitnics.exceptions;

public class InvalidFoodException extends RuntimeException {

    public InvalidFoodException(String error) {
        super("Unable to access Food data:\n" + error);
    }
}
