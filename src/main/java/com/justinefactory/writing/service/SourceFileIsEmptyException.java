package com.justinefactory.writing.service;

import java.io.IOException;

public class SourceFileIsEmptyException extends IOException {


    SourceFileIsEmptyException(Throwable cause) {
        super(cause);
    }

    SourceFileIsEmptyException(Throwable cause, String message) {
        super(message, cause);
    }

    SourceFileIsEmptyException(String message) {
        super(message);
    }

}
