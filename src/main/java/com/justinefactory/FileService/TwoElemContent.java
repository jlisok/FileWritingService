package com.justinefactory.FileService;

import java.io.Serializable;

class TwoElemContent implements ContentToCsvLine<String>, Serializable {

    private final Long timeStamp;
    private final String randomString;

    TwoElemContent(Long tstmp, String rs) {
        this.timeStamp = tstmp;
        this.randomString = rs;
    }

    @Override
    public String[] varsToCsvLine() {
        return new String[]{timeStamp.toString(), randomString};
    }

    Long getTimeStamp() {
        return timeStamp;
    }

    String getRandomString() {
        return randomString;
    }
}
