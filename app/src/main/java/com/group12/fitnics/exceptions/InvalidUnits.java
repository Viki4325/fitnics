package com.group12.fitnics.exceptions;

public class InvalidUnits extends RuntimeException{
    public InvalidUnits(String error) {
        super("Numbers out of range. Number  not supported: \n"+error);
    }
}
