package com.justinefactory.writing.service;

public class ContentReadingException extends Exception {

    ContentReadingException(Throwable cause) {
        super(cause);
    }

    ContentReadingException(Throwable cause, String message) {
        super(message, cause);
    }

    ContentReadingException(String message) {
        super(message);
    }

}
