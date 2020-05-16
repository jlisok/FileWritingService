package com.justinefactory.FileService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

class TwoElemContent implements ContentParser<String>, Serializable {

    private final Long timeStamp;
    private final String randomString;

    TwoElemContent(Long tstmp, String rs) {
        this.timeStamp = tstmp;
        this.randomString = rs;
    }


    @Override
    public String parseVars() {

        List<String> varList = Arrays.asList(timeStamp.toString(), randomString);
        String stringLine = (String) varList.stream().collect(Collectors.joining(","));
        return stringLine;

    }

    Long getTimeStamp() {
        return timeStamp;
    }

    String getRandomString() {
        return randomString;
    }


}
