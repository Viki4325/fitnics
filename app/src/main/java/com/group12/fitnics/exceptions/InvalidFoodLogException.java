package com.group12.fitnics.exceptions;

public class InvalidFoodLogException extends RuntimeException {

    public InvalidFoodLogException(String error) {
        super("Invalid FoodLog:\n" + error);
    }
}
