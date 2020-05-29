package com.justinefactory.reading.exceptions;

public class SourceFileIsEmptyException extends ContentReadingException {

    public SourceFileIsEmptyException(Throwable cause) {
        super(cause);
    }

    public SourceFileIsEmptyException(Throwable cause, String message) {
        super(cause, message);
    }

    public SourceFileIsEmptyException(String message) {
        super(message);
    }

}
