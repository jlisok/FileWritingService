package com.justinefactory.writing.exceptions;

public class FileWithStringsToGenerateContentIsEmptyException extends ContentInitializationException {

    public FileWithStringsToGenerateContentIsEmptyException(Throwable cause) {
        super(cause);
    }

    public FileWithStringsToGenerateContentIsEmptyException(Throwable cause, String message) {
        super(cause, message);
    }

    public FileWithStringsToGenerateContentIsEmptyException(String message) {
        super(message);
    }

}
