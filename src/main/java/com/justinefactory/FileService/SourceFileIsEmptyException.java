package com.justinefactory.FileService;

public class SourceFileIsEmptyException extends ContentInitializationException {


    SourceFileIsEmptyException(Throwable cause) {
        super(cause);
    }

    SourceFileIsEmptyException(Throwable cause, String message) {
        super(cause, message);
    }

    SourceFileIsEmptyException(String message) {
        super(message);
    }

}
