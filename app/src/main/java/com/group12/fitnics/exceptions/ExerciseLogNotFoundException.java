package com.group12.fitnics.exceptions;

public class ExerciseLogNotFoundException extends RuntimeException {

    public ExerciseLogNotFoundException(String error) {
        super("The exercise log is not found:\n" + error);
    }
}
