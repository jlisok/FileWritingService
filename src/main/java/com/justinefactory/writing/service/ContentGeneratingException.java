package com.justinefactory.writing.service;

class ContentGeneratingException extends ContentInitializationException {

    ContentGeneratingException(Throwable cause) {
        super(cause);
    }

    ContentGeneratingException(Throwable cause, String message) {
        super(cause, message);
    }

    ContentGeneratingException(String message) {
        super(message);
    }

}
