package com.justinefactory.domain;

import com.justinefactory.reading.exceptions.ContentStoringException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ThreeElemContentTest {

    @Test
    void createThreeElemContentWhen3ElementsMeetsConditions() throws ContentStoringException {
        //given
        Integer randomInt = 5;
        Long timeStamp = 4567890L;
        String randomString = "Tiger";

        //when
        ThreeElemContent threeElemContent = new ThreeElemContent(timeStamp, randomInt, randomString);

        //then
        assertEquals(randomInt, threeElemContent.getRandomInt());
        assertEquals(timeStamp, threeElemContent.getTimeStamp());
        assertEquals(randomString, threeElemContent.getRandomString());
    }

    @Test
    void createThreeElemContentWhenLongIsNull() {
        //given
        Integer randomInt = 5;
        String randomString = "Tiger";

        //then
        assertThrows(ContentStoringException.class, ()-> new ThreeElemContent(null, randomInt, randomString));
    }

    @Test
    void createThreeElemContentWhenIntegerIsNull() {
        //given
        Long timeStamp = 4567890L;
        String randomString = "Tiger";

        //then
        assertThrows(ContentStoringException.class, ()-> new ThreeElemContent(timeStamp, null, randomString));
    }

    @Test
    void createThreeElemContentWhenStringIsNull() {
        //given
        Integer randomInt = 5;
        Long timeStamp = 4567890L;

        //then
        assertThrows(ContentStoringException.class, ()-> new ThreeElemContent(timeStamp, randomInt, null));
    }

    @Test
    void createThreeElemContentWhenStringIsEmpty() {
        //given
        Integer randomInt = 5;
        Long timeStamp = 4567890L;
        String randomString = "";

        //then
        assertThrows(ContentStoringException.class, ()-> new ThreeElemContent(timeStamp, randomInt, randomString));
    }


}