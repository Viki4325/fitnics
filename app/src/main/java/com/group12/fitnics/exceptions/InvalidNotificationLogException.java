package com.group12.fitnics.exceptions;

public class InvalidNotificationLogException extends RuntimeException {

    public InvalidNotificationLogException(String error) {
        super("Invalid NotificationLog:\n" + error);
    }
}
