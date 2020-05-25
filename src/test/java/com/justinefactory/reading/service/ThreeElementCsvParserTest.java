package com.justinefactory.reading.service;

import com.justinefactory.domain.ThreeElemContent;
import com.justinefactory.reading.service.exceptions.ContentParsingException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ThreeElementCsvParserTest {

    @Test
    void parseLineWhenContentMeetsConditions() throws Exception {
        //given
        List<String[]> content = new ArrayList<>();
        content.add(new String[]{"1590147349818750700", "-840762737", "ChristopherRobin"});
        content.add(new String[]{"1590147349820800800","-1345882450","Owl"});
        content.add(new String[]{"1590147349822277700","1434010513","Heffalumps"});
        content.add(new String[]{"1590147349823733800","1822510187","Woozles"});

        List<ThreeElemContent> expectedContent = new ArrayList<>();
        expectedContent.add(new ThreeElemContent(1590147349818750700L,-840762737,"ChristopherRobin"));
        expectedContent.add(new ThreeElemContent(1590147349820800800L,-1345882450,"Owl"));
        expectedContent.add(new ThreeElemContent(1590147349822277700L,1434010513,"Heffalumps"));
        expectedContent.add(new ThreeElemContent(1590147349823733800L,1822510187,"Woozles"));

        //when
        ThreeElementCsvParser csvParser = new ThreeElementCsvParser();

        //then
        for (int i=0;i<content.size();i++) {
            ThreeElemContent csvLine = csvParser.parseLine(content.get(i));
            assertTrue(csvLine.equals(expectedContent.get(i)));
        }
    }


    @Test
    void parseLineWhenLineInFileCorrupt() {
        //given
        List<String[]> content = new ArrayList<>();
        content.add(new String[]{"1590147349818750700","-840762737","ChristopherRobin"});
        content.add(new String[]{"-1345882450","Owl"});

        //when
        ThreeElementCsvParser csvParser = new ThreeElementCsvParser();

        //then
        assertThrows(ContentParsingException.class, () -> csvParser.parseLine(content.get(1)));
    }

    @Test
    void parseLineWhenColumnsAreMisplacedInFile() {
        //given
        List<String[]> content = new ArrayList<>();
        content.add(new String[]{"1590147349818750700","-840762737","ChristopherRobin"});
        content.add(new String[]{"-1345882450","Owl","1590147349820800800"});

        //when
        ThreeElementCsvParser csvParser = new ThreeElementCsvParser();

        //then
        assertThrows(ContentParsingException.class, () -> csvParser.parseLine(content.get(1)));
    }
}
