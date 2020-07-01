package com.justinefactory.reading.readers;

import com.justinefactory.domain.PathInfo;
import com.justinefactory.domain.ThreeElemContent;
import com.justinefactory.reading.parsers.IntegerPlainContentParser;
import com.justinefactory.reading.parsers.ThreeElementCsvParser;
import com.justinefactory.reading.service.ContentReadingService;
import com.justinefactory.writing.domain.Content;
import com.justinefactory.writing.domain.CsvContent;
import com.justinefactory.writing.domain.PlainContent;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static com.justinefactory.testutil.PathToResourcesGetter.getPathToResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ContentReadingServiceTest {

    @Test
    void processContentWhenProcessingCSVFile() throws Exception {
        //given
        Path filePath = getPathToResource("example-csv-file-for-tests.csv");
        Content<ThreeElemContent> expectedContent = new Content<>();
        expectedContent.addContent(new ThreeElemContent(Long.parseLong("1590147349818750700"), -840762737, "ChristopherRobin"));
        expectedContent.addContent(new ThreeElemContent(Long.parseLong("1590147349820800800"), -1345882450, "Owl"));
        expectedContent.addContent(new ThreeElemContent(Long.parseLong("1590147349822277700"), 1434010513, "Heffalumps"));
        expectedContent.addContent(new ThreeElemContent(Long.parseLong("1590147349823733800"), 1822510187, "Woozles"));


        //when
        PathInfo fileData = new PathInfo(filePath);
        CsvContentReader contentReader = new CsvContentReader(fileData);
        ThreeElementCsvParser csvParser = new ThreeElementCsvParser();
        ContentReadingService<CsvContent, ThreeElemContent> contentReadingService = new ContentReadingService<>(contentReader, csvParser);
        Content<ThreeElemContent> content = contentReadingService.processContent();


        //then
        assertEquals(content, expectedContent);
    }

    @Test
    void processContentWhenProcessingPlainFile() throws Exception {
        //given
        PathInfo fileData = new PathInfo(getPathToResource("txt-file-content-integers.txt"));
        Content<Integer> expectedContent = new Content<>();
        expectedContent.addContent(40000000);
        expectedContent.addContent(800000);
        expectedContent.addContent(3245);
        expectedContent.addContent(2143567);

        //when
        PlainContentReader contentReader = new PlainContentReader(fileData);
        IntegerPlainContentParser intParser = new IntegerPlainContentParser();
        ContentReadingService<PlainContent, Integer> contentReadingService = new ContentReadingService<>(contentReader, intParser);
        Content<Integer> content = contentReadingService.processContent();


        //then
        assertEquals(content, expectedContent);
    }
}