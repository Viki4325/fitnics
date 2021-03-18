package com.group12.fitnics.exceptions;

public class InvalidExNameException extends InvalidExerciseException {

    public InvalidExNameException(String error) {
        super("The exercise name is invalid: \n" + error);
    }

}
