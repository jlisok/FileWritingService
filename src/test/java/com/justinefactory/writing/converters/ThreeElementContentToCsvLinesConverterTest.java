package com.justinefactory.writing.converters;

import com.justinefactory.domain.ThreeElemContent;
import com.justinefactory.writing.domain.Content;
import com.justinefactory.writing.domain.CsvContent;
import com.justinefactory.writing.exceptions.ContentConversion2ReadyToWriteException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ThreeElementContentToCsvLinesConverterTest {

    @Test
    void convertDataWhenContentIsEmpty() {
        //given
        ThreeElementContentToCsvLinesConverter converter = new ThreeElementContentToCsvLinesConverter();
        Content<ThreeElemContent> input = new Content<>();

        //then
        assertThrows(ContentConversion2ReadyToWriteException.class, () -> converter.convertContent(input));
    }


    @ParameterizedTest
    @MethodSource("conversionData")
    void convertDataWhenContentMeetsConditions(Content<ThreeElemContent> input, CsvContent expectedContent) throws ContentConversion2ReadyToWriteException {
        //given
        ThreeElementContentToCsvLinesConverter converter = new ThreeElementContentToCsvLinesConverter();

        //when
        CsvContent actualContent = converter.convertContent(input);

        //then
        for (int i = 0; i < expectedContent.getContent().size(); i++) {
            assertEquals(expectedContent.getContent(i)[0], actualContent.getContent(i)[0]);
            assertEquals(expectedContent.getContent(i)[1], actualContent.getContent(i)[1]);
            assertEquals(expectedContent.getContent(i)[2], actualContent.getContent(i)[2]);
        }
    }

    static Stream<Arguments> conversionData() {
        ThreeElemContent firstThreeElemContent = new ThreeElemContent(1590147349818750700L, 1345882450, "Owl");
        ThreeElemContent secondThreeElemContent = new ThreeElemContent(1590147349818759790L, -45882470, "Fluff");

        Content<ThreeElemContent> firstInput = new Content<>();
        firstInput.addContent(firstThreeElemContent);
        Content<ThreeElemContent> secondInput = firstInput;
        secondInput.addContent(secondThreeElemContent);

        CsvContent firstString = new CsvContent();
        firstString.addContent(new String[]{firstThreeElemContent.getTimeStamp().toString(), firstThreeElemContent.getRandomInt().toString(), firstThreeElemContent.getRandomString()});
        CsvContent secondString = firstString;
        secondString.addContent(new String[]{secondThreeElemContent.getTimeStamp().toString(), secondThreeElemContent.getRandomInt().toString(), secondThreeElemContent.getRandomString()});


        return Stream.of(
                Arguments.arguments(firstInput, firstString),
                Arguments.arguments(secondInput, secondString)
        );
    }

}