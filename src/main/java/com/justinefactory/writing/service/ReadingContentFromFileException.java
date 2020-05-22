package com.justinefactory.writing.service;

public class ReadingContentFromFileException extends ContentReadingException {

    ReadingContentFromFileException(Throwable cause) {
        super(cause);
    }

    ReadingContentFromFileException(Throwable cause, String message) {
        super(cause, message);
    }

    ReadingContentFromFileException(String message) {
        super(message);
    }

}
