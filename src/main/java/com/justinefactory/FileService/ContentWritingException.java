package com.justinefactory.FileService;

class ContentWritingException extends Exception {

    ContentWritingException(Throwable cause) {
        super(cause);
    }

    ContentWritingException(Throwable cause, String message) {
        super(message, cause);
    }

    ContentWritingException(String message) {
        super(message);
    }

}