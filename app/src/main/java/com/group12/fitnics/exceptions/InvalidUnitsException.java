package com.group12.fitnics.exceptions;

public class InvalidUnitsException extends RuntimeException{
    public InvalidUnitsException(String error) {
        super("Numbers out of range. Number  not supported: \n"+error);
    }
}
