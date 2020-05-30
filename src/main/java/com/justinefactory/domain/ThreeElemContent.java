package com.justinefactory.domain;

import java.util.Objects;

public class ThreeElemContent {

    private final Long timeStamp;
    private final Integer randomInt;
    private final String randomString;

    public ThreeElemContent(Long timeStamp, Integer randomInt, String randomString) {
        this.timeStamp = timeStamp;
        this.randomInt = randomInt;
        this.randomString = randomString;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThreeElemContent that = (ThreeElemContent) o;
        return Objects.equals(timeStamp, that.timeStamp) &&
                Objects.equals(randomInt, that.randomInt) &&
                Objects.equals(randomString, that.randomString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeStamp, randomInt, randomString);
    }

    public String getRandomString() {
        return randomString;
    }

    public Integer getRandomInt() {
        return randomInt;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }
}


