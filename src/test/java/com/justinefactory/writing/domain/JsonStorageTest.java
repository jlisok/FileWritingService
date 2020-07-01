package com.justinefactory.writing.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JsonStorageTest {

    @Test
    public void createJsonStorageWhenJsonIsNull() {
        //given
        String json = null;

        //then
        assertThrows(IllegalArgumentException.class, () -> new JsonReadyForJsonWriter(json));
    }


    @Test
    public void createJsonStorageWhenJsonIsEmpty() {
        //given
        String json = "";

        //then
        assertThrows(IllegalArgumentException.class, () -> new JsonReadyForJsonWriter(json));
    }


    @Test
    public void createJsonStorageWhenJsonMeetsConditions() {
        //given
        String json = "{\"content\": [\n" +
                "      {\n" +
                "        \"timeStamp\": 1590147349818750700,\n" +
                "        \"randomInt\": -840762737,\n" +
                "        \"randomString\": \"ChristopherRobin\"\n" +
                "      }\n" +
                "    ]\n";

        //when
        JsonReadyForJsonWriter jsonStorage = new JsonReadyForJsonWriter(json);

        //then
        assertEquals(json, jsonStorage.getContent());
    }

}