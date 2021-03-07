package com.group12.fitnics.exceptions;

public class ExerciseNotFoundException extends RuntimeException {

    public ExerciseNotFoundException(String error) {
        super("The exercise is not found:\n" + error);
    }
}
