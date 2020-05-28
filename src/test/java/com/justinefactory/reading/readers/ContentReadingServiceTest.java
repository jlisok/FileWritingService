package com.justinefactory.reading.readers;

import com.justinefactory.domain.FileData;
import com.justinefactory.domain.ThreeElemContent;
import com.justinefactory.reading.parsers.IntegerPlainContentParser;
import com.justinefactory.reading.parsers.ThreeElementCsvParser;
import com.justinefactory.reading.service.ContentReadingService;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.justinefactory.testutil.PathToResourcesGetter.getPathToResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ContentReadingServiceTest {

    @Test
    void processContentWhenProcessingCSVFile() throws Exception {
        //given
        Path filePath = getPathToResource("example-csv-file-for-tests.csv");
        List<ThreeElemContent> expectedContent = new ArrayList<>();
        expectedContent.add(new ThreeElemContent(Long.parseLong("1590147349818750700"), -840762737, "ChristopherRobin"));
        expectedContent.add(new ThreeElemContent(Long.parseLong("1590147349820800800"), -1345882450, "Owl"));
        expectedContent.add(new ThreeElemContent(Long.parseLong("1590147349822277700"), 1434010513, "Heffalumps"));
        expectedContent.add(new ThreeElemContent(Long.parseLong("1590147349823733800"), 1822510187, "Woozles"));


        //when
        FileData fileData = new FileData(filePath);
        CsvContentReader contentReader = new CsvContentReader(fileData);
        ThreeElementCsvParser csvParser = new ThreeElementCsvParser();
        ContentReadingService contentReadingService = new ContentReadingService(contentReader, csvParser);
        List<ThreeElemContent> content = contentReadingService.processContent();


        //then
        assertEquals(content, expectedContent);
    }

    @Test
    void processContentWhenProcessingPlainFile() throws Exception {
        //given
        FileData fileData = new FileData(getPathToResource("txt-file-content-integers.txt"));
        List<Integer> expectedContent = Arrays.asList(40000000,800000,3245,2143567);

        //when
        PlainContentReader contentReader = new PlainContentReader(fileData);
        IntegerPlainContentParser intParser = new IntegerPlainContentParser();
        ContentReadingService contentReadingService = new ContentReadingService(contentReader, intParser);
        List<Integer> content = contentReadingService.processContent();


        //then
        assertEquals(content, expectedContent);
    }
}