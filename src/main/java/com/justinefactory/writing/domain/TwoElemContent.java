package com.justinefactory.writing.domain;

import java.util.Objects;

public class TwoElemContent {

    private final Long timeStamp;
    private final String randomString;

    public TwoElemContent(Long timeStamp, String randomString) {
        this.timeStamp = timeStamp;
        this.randomString = randomString;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwoElemContent that = (TwoElemContent) o;
        return Objects.equals(timeStamp, that.timeStamp) &&
                Objects.equals(randomString, that.randomString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeStamp, randomString);
    }

    public String getRandomString() {
        return randomString;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }
}


