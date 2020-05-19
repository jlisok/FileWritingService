package com.justinefactory.FileService;

class ContentGeneratingException extends Exception {

    ContentGeneratingException(Throwable cause) {
        super(cause);
    }

    ContentGeneratingException(Throwable cause, String message) {
        super(message, cause);
    }

}
