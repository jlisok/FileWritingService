package com.justinefactory.reading.service.exceptions;

public class ContentParsingException extends ContentReadingException {

    public ContentParsingException(Throwable cause) {
        super(cause);
    }

    public ContentParsingException(Throwable cause, String message) {
        super(cause, message);
    }

    public ContentParsingException(String message) {
        super(message);
    }

}