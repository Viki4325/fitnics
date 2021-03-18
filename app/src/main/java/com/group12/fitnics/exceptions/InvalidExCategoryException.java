package com.group12.fitnics.exceptions;

public class InvalidExCategoryException extends InvalidExerciseException  {

    public InvalidExCategoryException(String error) {
        super("The exercise category is invalid: \n" + error);
    }
}
