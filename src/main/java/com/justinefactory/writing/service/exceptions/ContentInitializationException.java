package com.justinefactory.writing.service.exceptions;

public class ContentInitializationException extends Exception {

    public ContentInitializationException(Throwable cause) {
        super(cause);
    }

    public ContentInitializationException(Throwable cause, String message) {
        super(message, cause);
    }

    public ContentInitializationException(String message) {
        super(message);
    }

}
