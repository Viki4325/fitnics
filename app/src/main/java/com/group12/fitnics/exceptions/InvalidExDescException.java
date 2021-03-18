package com.group12.fitnics.exceptions;

public class InvalidExDescException extends InvalidExerciseException {

    public InvalidExDescException(String error) {
        super("The exercise description is invalid: \n" + error);
    }

}
