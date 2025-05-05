package com.mx.aplazo.BNPL.exception;

public class NotFoundLoanException extends RuntimeException {
    public NotFoundLoanException(String message) {
        super(message);
    }
}
