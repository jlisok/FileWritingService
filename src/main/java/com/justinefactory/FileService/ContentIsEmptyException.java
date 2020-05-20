package com.justinefactory.FileService;

public class ContentIsEmptyException extends Exception {

    ContentIsEmptyException(){    }

    ContentIsEmptyException(Throwable cause) {
        super(cause);
    }

    ContentIsEmptyException(Throwable cause, String message) {
        super(message, cause);
    }

}
