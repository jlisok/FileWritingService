package com.justinefactory.writing.converters;

import com.justinefactory.reading.exceptions.ContentStoringException;
import com.justinefactory.writing.domain.ContentStorage;
import com.justinefactory.writing.exceptions.ContentConversion2ReadyToWriteException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

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
        ContentStorage<Integer> input = new ContentStorage<>();

        //then
        assertThrows(ContentConversion2ReadyToWriteException.class, () -> converter.convertContent(input));
    }


    @ParameterizedTest
    @MethodSource("conversionData")
    void convertDataWhenContentMeetsConditions(ContentStorage<Integer> input, ContentStorage<String> expectedContent) throws ContentConversion2ReadyToWriteException, ContentStoringException {
        //given
        IntegersToLinesConverter converter = new IntegersToLinesConverter();

        //when
        ContentStorage<String> actualContent = converter.convertContent(input);

        //then
        assertEquals(actualContent, expectedContent);
    }

    static Stream<Arguments> conversionData() throws ContentStoringException {
        return Stream.of(
                Arguments.arguments(new ContentStorage<>(Collections.singletonList(1)), new ContentStorage<>(Collections.singletonList("1"))),
                Arguments.arguments(new ContentStorage<>(Arrays.asList(1, 2)), new ContentStorage<>(Arrays.asList("1", "2"))),
                Arguments.arguments(new ContentStorage<>(Arrays.asList(1, 2, 3, 4)), new ContentStorage<>(Arrays.asList("1", "2", "3", "4")))
        );
    }

}