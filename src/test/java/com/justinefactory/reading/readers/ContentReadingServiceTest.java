package com.justinefactory.reading.readers;

import com.justinefactory.domain.PathData;
import com.justinefactory.domain.ThreeElemContent;
import com.justinefactory.reading.parsers.IntegerPlainContentParser;
import com.justinefactory.reading.parsers.ThreeElementCsvParser;
import com.justinefactory.reading.service.ContentReadingService;
import com.justinefactory.writing.domain.ContentStorage;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static com.justinefactory.testutil.PathToResourcesGetter.getPathToResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ContentReadingServiceTest {

    @Test
    void processContentWhenProcessingCSVFile() throws Exception {
        //given
        Path filePath = getPathToResource("example-csv-file-for-tests.csv");
        ContentStorage<ThreeElemContent> expectedContent = new ContentStorage<>();
        expectedContent.addContent(new ThreeElemContent(Long.parseLong("1590147349818750700"), -840762737, "ChristopherRobin"));
        expectedContent.addContent(new ThreeElemContent(Long.parseLong("1590147349820800800"), -1345882450, "Owl"));
        expectedContent.addContent(new ThreeElemContent(Long.parseLong("1590147349822277700"), 1434010513, "Heffalumps"));
        expectedContent.addContent(new ThreeElemContent(Long.parseLong("1590147349823733800"), 1822510187, "Woozles"));


        //when
        PathData fileData = new PathData(filePath);
        CsvContentReader contentReader = new CsvContentReader(fileData);
        ThreeElementCsvParser csvParser = new ThreeElementCsvParser();
        ContentReadingService contentReadingService = new ContentReadingService(contentReader, csvParser);
        ContentStorage<ThreeElemContent> content = contentReadingService.processContent();


        //then
        assertEquals(content, expectedContent);
    }

    @Test
    void processContentWhenProcessingPlainFile() throws Exception {
        //given
        PathData fileData = new PathData(getPathToResource("txt-file-content-integers.txt"));
        ContentStorage<Integer> expectedContent = new ContentStorage<>();
        expectedContent.addContent(40000000);
        expectedContent.addContent(800000);
        expectedContent.addContent(3245);
        expectedContent.addContent(2143567);

        //when
        PlainContentReader contentReader = new PlainContentReader(fileData);
        IntegerPlainContentParser intParser = new IntegerPlainContentParser();
        ContentReadingService contentReadingService = new ContentReadingService(contentReader, intParser);
        ContentStorage<Integer> content = contentReadingService.processContent();


        //then
        assertEquals(content, expectedContent);
    }
}