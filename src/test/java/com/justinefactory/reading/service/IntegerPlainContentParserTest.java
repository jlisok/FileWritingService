package com.justinefactory.reading.service;

import com.justinefactory.reading.service.exceptions.ContentParsingException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IntegerPlainContentParserTest {

    @Test
    void parseLineWhenContentMeetsConditions() throws Exception {
        //given
        List<Integer> items = Arrays.asList(40000000, 800000, 3245);
        List<Integer> expectedContent = new ArrayList<>();
        expectedContent.addAll(items);

        List<String> content = Arrays.asList(expectedContent.get(0).toString(), expectedContent.get(1).toString(), expectedContent.get(2).toString());

        //when
        IntegerPlainContentParser contentParser = new IntegerPlainContentParser();
        List<Integer> parsedContent = new ArrayList<>();
        for (String item : content) {
            Integer integer = contentParser.parseLine(item);
            parsedContent.add(integer);
        }

        //then
        assertTrue(parsedContent.equals(expectedContent));
    }


    @Test
    void parseLineWhenContentDoesNotMeetConditions() throws Exception {
        //given
        List<String> content = Arrays.asList("alex", "brian", "charles");

        //when
        IntegerPlainContentParser contentParser = new IntegerPlainContentParser();

        //then
        for (String item : content) {
            assertThrows(ContentParsingException.class, () -> contentParser.parseLine(item));
        }
    }
}