package com.justinefactory.reading.exceptions;

public class ContentStoringException extends Exception {

    public ContentStoringException(Throwable cause) {
        super(cause);
    }

    public ContentStoringException(Throwable cause, String message) {
        super(message, cause);
    }

    public ContentStoringException(String message) {
        super(message);
    }
}
