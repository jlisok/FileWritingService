package com.justinefactory.util;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class TimeCalculator {

    public Date calculateExpirationTime(Instant now, Duration duration) {
        if (duration.isNegative()) {
            throw new IllegalArgumentException("Delta time cannot be negative.");
        }
        Long nowInMillis = now.toEpochMilli();
        Date expiration = new Date();
        Long deltaTimeInMillis = duration.toMillis();
        expiration.setTime(nowInMillis + deltaTimeInMillis);
        return expiration;
    }

}
