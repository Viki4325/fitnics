package com.group12.fitnics.exceptions;

/**
 * java.sql.SQLException is a checked exception, so it needs to be wrapped
 * the regular SQLException in an unchecked RuntimeException so that we can throw them around,
 * but they don't have to be caught thrown towards the calling method.
 */

public class HSQLDBException extends RuntimeException
{
    public HSQLDBException(Exception cause) { super(cause); }
}
