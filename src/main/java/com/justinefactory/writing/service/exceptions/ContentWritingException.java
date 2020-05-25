package com.justinefactory.writing.service.exceptions;

public class ContentWritingException extends Exception {

    public ContentWritingException(Throwable cause) {
        super(cause);
    }

    public ContentWritingException(Throwable cause, String message) {
        super(message, cause);
    }

    public ContentWritingException(String message) {
        super(message);
    }

}