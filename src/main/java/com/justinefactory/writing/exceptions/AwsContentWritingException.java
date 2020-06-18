package com.justinefactory.writing.exceptions;

public class AwsContentWritingException extends ContentWritingException {

    public AwsContentWritingException(Throwable cause, String message) {
        super(cause, message);
    }

    public AwsContentWritingException(String message) {
        super(message);
    }

}
