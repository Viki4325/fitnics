package com.group12.fitnics.exceptions;

public class InvalidNotificationException extends RuntimeException {

    public InvalidNotificationException(String error) {
        super("Unable to access Notification data:\n" + error);
    }
}
