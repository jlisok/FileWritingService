package com.justinefactory.reading.parsers;

import com.justinefactory.reading.exceptions.ContentParsingException;
import com.justinefactory.writing.domain.Content;
import com.justinefactory.writing.domain.PlainContent;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntegerPlainContentParserTest {

    @ParameterizedTest
    @MethodSource("parseLineWhenContentMeetsConditionsTestData")
    void parseLineWhenContentMeetsConditions(PlainContent content, Content<Integer> expectedContent) throws Exception {
        //when
        IntegerPlainContentParser contentParser = new IntegerPlainContentParser();
        Content<Integer> integer = contentParser.parse(content);

        //then
        assertEquals(expectedContent, integer);
    }

    static Stream<Arguments> parseLineWhenContentMeetsConditionsTestData() {
        return Stream.of(
                Arguments.arguments(new PlainContent(List.of("40000000")), new Content<>(40000000)),
                Arguments.arguments(new PlainContent(List.of("800000")), new Content<>(800000)),
                Arguments.arguments(new PlainContent(List.of("3245")), new Content<>(3245)),
                Arguments.arguments(new PlainContent(List.of("0")), new Content<>(0)),
                Arguments.arguments(new PlainContent(List.of("-765432")), new Content<>(-765432))
        );
    }


    @ParameterizedTest
    @MethodSource("parseLineWhenContentDoesNotMeetConditionsTestData")
    void parseLineWhenContentDoesNotMeetConditions(PlainContent content) {
        //when
        IntegerPlainContentParser contentParser = new IntegerPlainContentParser();

        //then
        assertThrows(ContentParsingException.class, () -> contentParser.parse(content));
    }

    static Stream<Arguments> parseLineWhenContentDoesNotMeetConditionsTestData() {
        return Stream.of(
                Arguments.arguments(new PlainContent(List.of("Plain"))),
                Arguments.arguments(new PlainContent(List.of("Raw")))
        );
    }
}