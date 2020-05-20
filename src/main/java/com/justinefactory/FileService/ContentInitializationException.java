package com.justinefactory.FileService;

public class ContentInitializationException extends Exception {

    ContentInitializationException(Throwable cause) {
        super(cause);
    }

    ContentInitializationException(Throwable cause, String message) {
        super(message, cause);
    }

    ContentInitializationException(String message) {
        super(message);
    }

}
