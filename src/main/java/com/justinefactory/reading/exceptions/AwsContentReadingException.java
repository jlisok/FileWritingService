package com.justinefactory.reading.exceptions;

public class AwsContentReadingException extends ContentReadingException {

    public AwsContentReadingException(Throwable cause) {
        super(cause);
    }

    public AwsContentReadingException(Throwable cause, String message) {
        super(cause, message);
    }

    public AwsContentReadingException(String message) {
        super(message);
    }
}
