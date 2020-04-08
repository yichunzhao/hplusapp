package com.ynz.hplusapp.exceptions;

public class LoginFailureException extends RuntimeException {
    public LoginFailureException(String message) {
        super(message);
    }
}
