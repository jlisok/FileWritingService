package com.justinefactory.writing.converters;

import com.justinefactory.writing.domain.Content;
import com.justinefactory.writing.domain.PlainContent;
import com.justinefactory.writing.exceptions.ContentConversion2ReadyToWriteException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntegersToLinesConverterTest {

    @Test
    void convertDataWhenNull() {
        //given
        IntegersToLinesConverter converter = new IntegersToLinesConverter();

        //then
        assertThrows(ContentConversion2ReadyToWriteException.class, () -> converter.convertContent(null));
    }


    @Test
    void convertDataWhenIsEmpty() {
        //given
        IntegersToLinesConverter converter = new IntegersToLinesConverter();
        Content<Integer> input = new Content<>();

        //then
        assertThrows(ContentConversion2ReadyToWriteException.class, () -> converter.convertContent(input));
    }


    @ParameterizedTest
    @MethodSource("conversionData")
    void convertDataWhenContentMeetsConditions(Content<Integer> input, PlainContent expectedContent) throws ContentConversion2ReadyToWriteException {
        //given
        IntegersToLinesConverter converter = new IntegersToLinesConverter();

        //when
        PlainContent actualContent = converter.convertContent(input);

        //then
        assertEquals(actualContent, expectedContent);
    }

    static Stream<Arguments> conversionData() {
        return Stream.of(
                Arguments.arguments(new Content<>(Collections.singletonList(1)), new PlainContent(Collections.singletonList("1"))),
                Arguments.arguments(new Content<>(Arrays.asList(1, 2)), new PlainContent(Arrays.asList("1", "2"))),
                Arguments.arguments(new Content<>(Arrays.asList(1, 2, 3, 4)), new PlainContent(Arrays.asList("1", "2", "3", "4")))
        );
    }

}