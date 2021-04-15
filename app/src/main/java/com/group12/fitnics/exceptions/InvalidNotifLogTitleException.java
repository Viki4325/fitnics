package com.group12.fitnics.exceptions;

public class InvalidNotifLogTitleException extends InvalidNotificationException {
    public InvalidNotifLogTitleException(String error) {
        super("The title does not meet the requirement: \n" + error);

    }
}
