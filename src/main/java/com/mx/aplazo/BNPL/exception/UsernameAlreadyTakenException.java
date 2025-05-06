package com.mx.aplazo.BNPL.exception;

public class UsernameAlreadyTakenException extends RuntimeException {
    public UsernameAlreadyTakenException(String username) {
        super("Username '" + username + "' is already taken.");
    }
}
