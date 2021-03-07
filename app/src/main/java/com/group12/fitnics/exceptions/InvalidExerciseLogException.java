package com.group12.fitnics.exceptions;

public class InvalidExerciseLogException extends RuntimeException {

    public InvalidExerciseLogException(String error) {
        super("Invalid ExerciseLog:\n" + error);
    }
}
