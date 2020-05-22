package com.justinefactory.writing.service;

class ContentParsingException extends Exception {

    ContentParsingException(Throwable cause) {
        super(cause);
    }

    ContentParsingException(Throwable cause, String message) {
        super(message, cause);
    }

    ContentParsingException(String message) {
        super(message);
    }

}