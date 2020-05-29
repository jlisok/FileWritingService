package com.justinefactory.reading.parsers;

import com.justinefactory.reading.exceptions.ContentParsingException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IntegerPlainContentParserTest {

    @ParameterizedTest
    @MethodSource("parseLineWhenContentMeetsConditionsTestData")
    void parseLineWhenContentMeetsConditions(String content, Integer expectedContent) throws Exception {
        //when
        IntegerPlainContentParser contentParser = new IntegerPlainContentParser();
        Integer integer = contentParser.parseLine(content);

        //then
        assertEquals(integer, expectedContent);
    }

    static Stream<Arguments> parseLineWhenContentMeetsConditionsTestData() {
        return Stream.of(
                Arguments.arguments("40000000", 40000000),
                Arguments.arguments("800000", 800000),
                Arguments.arguments("3245", 3245),
                Arguments.arguments("0", 0),
                Arguments.arguments("-765432",-765432)
        );
    }


    @ParameterizedTest
    @ValueSource(strings = {"alex", "brian", "charles","-543,88763"})
    void parseLineWhenContentDoesNotMeetConditions(String content) {
        //when
        IntegerPlainContentParser contentParser = new IntegerPlainContentParser();

        //then
        assertThrows(ContentParsingException.class, () -> contentParser.parseLine(content));
    }
}