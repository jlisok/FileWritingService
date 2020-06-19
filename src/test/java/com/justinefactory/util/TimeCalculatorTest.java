package com.justinefactory.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeCalculatorTest {

    @Test
    void calculateExpirationTimeWhenDurationBelowZero() {
        //given
        TimeCalculator calculator = new TimeCalculator();
        Instant now = Instant.now();

        //when
        Duration duration = Duration.ofMinutes(-20);

        //then
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateExpirationTime(now, duration));
    }


    @ParameterizedTest
    @MethodSource("testData")
    void calculateExpirationTimeWhenDurationMeetsConditions(Instant now, Duration duration, Date expected) {
        //given
        TimeCalculator calculator = new TimeCalculator();

        //when
        Date actual = calculator.calculateExpirationTime(now, duration);

        //then
        assertEquals(expected, actual);

    }

    static Stream<Arguments> testData() {
        Instant now = Instant.now();
        return Stream.of(
                Arguments.arguments(now, Duration.ofMinutes(0), calculateExpected(now, 0)),
                Arguments.arguments(now, Duration.ofMinutes(10), calculateExpected(now, 10)),
                Arguments.arguments(now, Duration.ofMinutes(60), calculateExpected(now, 60)),
                Arguments.arguments(now, Duration.ofHours(24), calculateExpected(now, 60 * 24))
        );
    }

    private static Date calculateExpected(Instant now, Integer time) {
        Date expected = new Date();
        expected.setTime(now.toEpochMilli() + Duration.ofMinutes(time).toMillis());
        return expected;
    }


}