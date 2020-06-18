package com.justinefactory.sending.exceptions;

public class AwsSecurityCredentialsException extends Exception {

    public AwsSecurityCredentialsException(Throwable cause, String message) {
        super(message, cause);
    }

}
