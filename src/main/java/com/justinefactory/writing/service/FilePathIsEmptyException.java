package com.justinefactory.writing.service;

public class FilePathIsEmptyException extends ReadingContentFromFileException {

    FilePathIsEmptyException(Throwable cause) {
        super(cause);
    }

    FilePathIsEmptyException(Throwable cause, String message) {
        super(cause, message);
    }

    FilePathIsEmptyException(String message) {
        super(message);
    }

}
