package com.justinefactory.reading.service.exceptions;

public class ContentReadingException extends Exception {

    public ContentReadingException(Throwable cause) {
        super(cause);
    }

    public ContentReadingException(Throwable cause, String message) {
        super(message, cause);
    }

    public ContentReadingException(String message) {
        super(message);
    }

}
