package com.justinefactory.writing.service;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

import static com.justinefactory.testutil.PathToResourcesGetter.getPathToResource;
import static org.junit.jupiter.api.Assertions.*;

class ThreeElementCsvParserTest {

    @Test
    void parseLineWhenContentMeetsConditions() throws Exception {
        //given
        Path filePath = getPathToResource("exemplary-csv-file-for-tests.csv");

        //when
        FileData fileData = new FileData(filePath);
        CsvContentReader contentReader = new CsvContentReader(fileData);
        List<String[]> content = contentReader.readContent();
        ThreeElementCsvParser csvParser = new ThreeElementCsvParser();

        //then
        for (String[] item : content) {
            ThreeElemContent csvLine = csvParser.parseLine(item);
            assertNotNull(csvLine);
        }
    }


    @Test
    void parseLineWhenLineInFileCorrupt() throws Exception {
        //given
        Path filePath = getPathToResource("corrupted-csv-file.csv");

        //when
        FileData fileData = new FileData(filePath);
        CsvContentReader contentReader = new CsvContentReader(fileData);
        List<String[]> content = contentReader.readContent();
        ThreeElementCsvParser csvParser = new ThreeElementCsvParser();

        //then
        assertThrows(ContentParsingException.class, () -> csvParser.parseLine(content.get(1)));
    }


    @Test
    void parseLineWhenColumnsAreMisplacedInFile() throws Exception {
        //given
        Path filePath = getPathToResource("csv-file-misplaced-columns-ThreeElemContentLineParser.csv");

        //when
        FileData fileData = new FileData(filePath);
        CsvContentReader contentReader = new CsvContentReader(fileData);
        List<String[]> content = contentReader.readContent();
        ThreeElementCsvParser csvParser = new ThreeElementCsvParser();

        //then
        assertThrows(ContentParsingException.class, () -> csvParser.parseLine(content.get(1)));
    }
}
