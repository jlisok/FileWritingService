package com.justinefactory.reading.service.exceptions;

import java.io.IOException;

public class SourceFileIsEmptyException extends ContentReadingException {

    public SourceFileIsEmptyException(Throwable cause) {
        super(cause);
    }

    public SourceFileIsEmptyException(Throwable cause, String message) {
        super(cause, message);
    }

    public SourceFileIsEmptyException(String message) {
        super(message);
    }

}
