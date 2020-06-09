package com.justinefactory.sending.exceptions;

public class ContentSendingException extends Exception {

    public ContentSendingException(Throwable cause) {
        super(cause);
    }

    public ContentSendingException(Throwable cause, String message) {
        super(message, cause);
    }

    public ContentSendingException(String message) {
        super(message);
    }

}
