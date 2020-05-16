package com.justinefactory.FileService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class ThreeElemContent implements ContentParser<String>, Serializable {

    private final Long timeStamp;
    private final Integer randomInt;
    private final String randomString;

    ThreeElemContent(Long timeStamp, Integer randomInt, String randomString) {
        this.timeStamp = timeStamp;
        this.randomInt = randomInt;
        this.randomString = randomString;
    }


    @Override
    public String parseVars() {

        List<String> varList = Arrays.asList(timeStamp.toString(), randomInt.toString(), randomString);
        String stringLine = (String) varList.stream().collect(Collectors.joining(","));
        return stringLine;

    }



}
