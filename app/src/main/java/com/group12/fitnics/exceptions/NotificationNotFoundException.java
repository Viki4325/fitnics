package com.group12.fitnics.exceptions;

public class NotificationNotFoundException extends RuntimeException {

    public NotificationNotFoundException(String error) {
        super("The Notification is not found:\n" + error);
    }
}
