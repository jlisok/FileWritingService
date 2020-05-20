package com.justinefactory.FileService;

class TwoElemContent {

    private final Long timeStamp;
    private final String randomString;

    TwoElemContent(Long tstmp, String rs) {
        this.timeStamp = tstmp;
        this.randomString = rs;
    }

    Long getTimeStamp() {
        return timeStamp;
    }

    String getRandomString() {
        return randomString;
    }
}
