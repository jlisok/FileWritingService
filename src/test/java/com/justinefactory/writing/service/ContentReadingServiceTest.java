package com.justinefactory.writing.service;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.justinefactory.testutil.PathToResourcesGetter.getPathToResource;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContentReadingServiceTest {

    @Test
    void processContentWhenProcessingCSVFile() throws Exception {
        //given
        Path filePath = getPathToResource("exemplary-csv-file-for-tests.csv");
        List<ThreeElemContent> prepContent = new ArrayList<>();
        prepContent.add(new ThreeElemContent(Long.parseLong("1590147349818750700"), -840762737, "ChristopherRobin"));
        prepContent.add(new ThreeElemContent(Long.parseLong("1590147349820800800"), -1345882450, "Owl"));
        prepContent.add(new ThreeElemContent(Long.parseLong("1590147349822277700"), 1434010513, "Heffalumps"));
        prepContent.add(new ThreeElemContent(Long.parseLong("1590147349823733800"), 1822510187, "Woozles"));


        //when
        FileData fileData = new FileData(filePath);
        CsvContentReader contentReader = new CsvContentReader(fileData);
        ThreeElementCsvParser csvParser = new ThreeElementCsvParser();
        ContentReadingService contentReadingService = new ContentReadingService(contentReader, csvParser);
        List<ThreeElemContent> content = contentReadingService.processContent();


        //then
        assertTrue(content.equals(prepContent));
    }

    @Test
    void processContentWhenProcessingPlainFile() throws Exception {
        //given
        FileData fileData = new FileData(getPathToResource("txt-file-content-integers.txt"));
        List<Integer> prepContent = new ArrayList<>();
        prepContent.add(40000000);
        prepContent.add(800000);
        prepContent.add(3245);
        prepContent.add(2143567);

        //when
        PlainContentReader contentReader = new PlainContentReader(fileData);
        IntegerPlainContentParser intParser = new IntegerPlainContentParser();
        ContentReadingService contentReadingService = new ContentReadingService(contentReader, intParser);
        List<Integer> content = contentReadingService.processContent();


        //then
        assertTrue(content.equals(prepContent));
    }
}