package com.justinefactory.stats.exceptions;

public class StatsCalculatingException extends Exception {

    public StatsCalculatingException(Throwable cause) {
        super(cause);
    }

    public StatsCalculatingException(Throwable cause, String message) {
        super(message, cause);
    }

    public StatsCalculatingException(String message) {
        super(message);
    }
}
