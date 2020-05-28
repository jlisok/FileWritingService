package com.justinefactory.writing.exceptions;

public class ContentGeneratingException extends ContentInitializationException {

    public ContentGeneratingException(Throwable cause) {
        super(cause);
    }

    public ContentGeneratingException(Throwable cause, String message) {
        super(cause, message);
    }

    public ContentGeneratingException(String message) {
        super(message);
    }

}
