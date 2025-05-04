package com.mx.aplazo.BNPL.exception;

public class InvalidCreditLineException extends RuntimeException {
    public InvalidCreditLineException(String message) {
        super(message);
    }
}
