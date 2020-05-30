package com.justinefactory.writing.exceptions;

public class ContentConvertingToLineException extends ContentWritingException {

    public ContentConvertingToLineException(Throwable cause) {
        super(cause);
    }

    public ContentConvertingToLineException(Throwable cause, String message) {
        super(cause, message);
    }

    public ContentConvertingToLineException(String message) {
        super(message);
    }

}

