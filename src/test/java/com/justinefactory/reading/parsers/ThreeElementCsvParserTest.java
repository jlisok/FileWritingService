package com.justinefactory.reading.parsers;

import com.justinefactory.domain.ThreeElemContent;
import com.justinefactory.reading.exceptions.ContentParsingException;
import com.justinefactory.reading.exceptions.ContentStoringException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ThreeElementCsvParserTest {

    @ParameterizedTest
    @MethodSource("parseLineWhenContentMeetsConditionsTestData")
    void parseLineWhenContentMeetsConditions(String[] content, ThreeElemContent expectedContent) throws Exception {
        //when
        ThreeElementCsvParser csvParser = new ThreeElementCsvParser();

        //then
        ThreeElemContent csvLine = csvParser.parseLine(content);
        assertEquals(expectedContent, csvLine);
    }


    static Stream<Arguments> parseLineWhenContentMeetsConditionsTestData() throws ContentStoringException {
        return Stream.of(
                Arguments.arguments(new String[]{"1590147349818750700", "-840762737", "ChristopherRobin"}, new ThreeElemContent(1590147349818750700L, -840762737, "ChristopherRobin")),
                Arguments.arguments(new String[]{"1590147349820800800", "-1345882450", "Owl"}, new ThreeElemContent(1590147349820800800L, -1345882450, "Owl")),
                Arguments.arguments(new String[]{"1590147349822277700", "1434010513", "Heffalumps"}, new ThreeElemContent(1590147349822277700L, 1434010513, "Heffalumps")),
                Arguments.arguments(new String[]{"1590147349823733800", "1822510187", "Woozles"}, new ThreeElemContent(1590147349823733800L, 1822510187, "Woozles"))
        );
    }


    @ParameterizedTest
    @MethodSource("parseLineWhenLineInFileCorruptTestData")
    void parseLineWhenLineInFileCorrupt(String[] content) {
        //when
        ThreeElementCsvParser csvParser = new ThreeElementCsvParser();

        //then
        assertThrows(ContentParsingException.class, () -> csvParser.parseLine(content));
    }

    static Stream<Arguments> parseLineWhenLineInFileCorruptTestData() {
        return Stream.of(
                Arguments.of((Object) new String[]{"1590147349818750700", "ChristopherRobin"}),
                Arguments.of((Object) new String[]{"1590147349818750700", "840762737", "ChristopherRobin", "ChristopherRobin"})
        );
    }


    @ParameterizedTest
    @MethodSource("parseLineWhenColumnsAreMisplacedInFileTestData")
    void parseLineWhenColumnsAreMisplacedInFile(String[] content) {
        //when
        ThreeElementCsvParser csvParser = new ThreeElementCsvParser();

        //then
        assertThrows(ContentParsingException.class, () -> csvParser.parseLine(content));
    }

    static Stream<Arguments> parseLineWhenColumnsAreMisplacedInFileTestData() {
        return Stream.of(
                Arguments.of((Object) new String[]{"-1345882450", "Owl", "1590147349820800800"})
        );
    }
}
