package com.justinefactory.domain;

public class TwoElemContent {

    private final Long timeStamp;
    private final String randomString;

    public TwoElemContent(Long tstmp, String rs) {
        this.timeStamp = tstmp;
        this.randomString = rs;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public String getRandomString() {
        return randomString;
    }
}
