package com.group12.fitnics.exceptions;

public class FoodNotFoundException extends RuntimeException {

    public FoodNotFoundException(String error) {
        super("The food is not found:\n" + error);
    }
}
