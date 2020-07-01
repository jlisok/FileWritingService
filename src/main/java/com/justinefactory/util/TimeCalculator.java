package com.justinefactory.util;

import java.time.Duration;
import java.time.Instant;

public class TimeCalculator {

    public Instant calculateExpirationTime(Instant now, Duration duration) {
        if (duration.isNegative()) {
            throw new IllegalArgumentException("Delta time cannot be negative.");
        }
        return now.plus(duration);
    }
}
