package com.justinefactory.FileService;

import java.io.Serializable;

class ThreeElemContent implements ContentToCsvLine<String>, Serializable {

    private final Long timeStamp;
    private final Integer randomInt;
    private final String randomString;

    ThreeElemContent(Long timeStamp, Integer randomInt, String randomString) {
        this.timeStamp = timeStamp;
        this.randomInt = randomInt;
        this.randomString = randomString;
    }

    @Override
    public String[] varsToCsvLine() {
        return new String[]{timeStamp.toString(), randomInt.toString(), randomString};
    }
}
