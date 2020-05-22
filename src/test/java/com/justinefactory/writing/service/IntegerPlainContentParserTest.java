package com.justinefactory.writing.service;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.justinefactory.testutil.PathToResourcesGetter.getPathToResource;
import static org.junit.jupiter.api.Assertions.*;

class IntegerPlainContentParserTest {

    @Test
    void parseLineWhenContentMeetsConditions() throws Exception {
        //given
        FileData fileData = new FileData(getPathToResource("txt-file-content-integers.txt"));
        List<Integer> prepContent = new ArrayList<>();
        prepContent.add(40000000);
        prepContent.add(800000);
        prepContent.add(3245);
        prepContent.add(2143567);


        //when
        PlainContentReader contentReader = new PlainContentReader(fileData);
        List<String> content = contentReader.readContent();
        IntegerPlainContentParser contentParser = new IntegerPlainContentParser();
        List<Integer> parsedContent = new ArrayList<>();
        for (String item : content) {
            Integer integer = contentParser.parseLine(item);
            parsedContent.add(integer);
        }

        //then
        assertTrue(parsedContent.equals(prepContent));
    }


    @Test
    void parseLineWhenContentDoesNotMeetConditions() throws Exception {
        //given
        FileData fileData = new FileData(getPathToResource("txt-file-non-integer-content.txt"));

        //when
        PlainContentReader contentReader = new PlainContentReader(fileData);
        List<String> content = contentReader.readContent();
        IntegerPlainContentParser contentParser = new IntegerPlainContentParser();
        List<Integer> parsedContent = new ArrayList<>();


        //then
        for (String item : content) {
            assertThrows(ContentParsingException.class, () -> contentParser.parseLine(item));
        }
    }
}