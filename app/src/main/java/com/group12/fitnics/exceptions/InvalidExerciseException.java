package com.group12.fitnics.exceptions;

public class InvalidExerciseException extends RuntimeException {

    public InvalidExerciseException(String error) {
        super("Unable to access Food data:\n" + error);
    }
}
