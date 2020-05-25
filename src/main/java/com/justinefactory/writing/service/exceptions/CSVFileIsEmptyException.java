package com.justinefactory.writing.service.exceptions;

public class CSVFileIsEmptyException extends ContentInitializationException {

    public CSVFileIsEmptyException(Throwable cause) {
        super(cause);
    }

    public CSVFileIsEmptyException(Throwable cause, String message) {
        super(cause, message);
    }

    public CSVFileIsEmptyException(String message) {
        super(message);
    }

}
