package com.group12.fitnics.exceptions;

public class InvalidExLevelException extends InvalidExerciseException {

    public InvalidExLevelException(String error) {
        super("The exercise level is invalid: \n" + error);
    }
}
