package com.group12.fitnics.exceptions;

public class NotificationLogNotFoundException extends RuntimeException {

    public NotificationLogNotFoundException(String error) {
        super("The Notification log is not found:\n" + error);
    }
}
