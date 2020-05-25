package com.justinefactory.reading.service.exceptions;

public class ReadingContentFromFileException extends ContentReadingException {

    public ReadingContentFromFileException(Throwable cause) {
        super(cause);
    }

    public ReadingContentFromFileException(Throwable cause, String message) {
        super(cause, message);
    }

    public ReadingContentFromFileException(String message) {
        super(message);
    }

}
