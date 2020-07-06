package com.justinefactory.writing.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JsonContentTest {

    @Test
    public void createJsonContentWhenJsonIsNull() {
        //given
        String json = null;

        //then
        assertThrows(IllegalArgumentException.class, () -> new JsonContent(json));
    }


    @Test
    public void createJsonContentWhenJsonIsEmpty() {
        //given
        String json = "";

        //then
        assertThrows(IllegalArgumentException.class, () -> new JsonContent(json));
    }


    @Test
    public void createJsonContentWhenJsonMeetsConditions() {
        //given
        String json = "{\"content\": [\n" +
                "      {\n" +
                "        \"timeStamp\": 1590147349818750700,\n" +
                "        \"randomInt\": -840762737,\n" +
                "        \"randomString\": \"ChristopherRobin\"\n" +
                "      }\n" +
                "    ]\n";

        //when
        JsonContent jsonContent = new JsonContent(json);

        //then
        assertEquals(json, jsonContent.getContent());
    }

}