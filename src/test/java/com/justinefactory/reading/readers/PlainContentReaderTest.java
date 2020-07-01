package com.justinefactory.reading.readers;

import com.justinefactory.domain.PathInfo;
import com.justinefactory.reading.exceptions.SourceFileIsEmptyException;
import com.justinefactory.writing.domain.PlainContent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Arrays;

import static com.justinefactory.testutil.PathToResourcesGetter.getPathToResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlainContentReaderTest {

    @Test
    void readContentWhenFileContentMeetsConditions() throws Exception {
        //given
        PathInfo fileData = new PathInfo(getPathToResource("txt-file-content-integers.txt"));
        PlainContent expectedContent = new PlainContent(Arrays.asList("40000000", "800000", "3245", "2143567"));

        //when
        PlainContentReader contentReader = new PlainContentReader(fileData);
        PlainContent content = contentReader.readContent();

        //then
        assertEquals(content, expectedContent);

    }

    @Test
    void readContentWhenFileIsEmpty() throws Exception {
        //given
        Path filePath = getPathToResource("empty-string-content.csv");
        PathInfo fileData = new PathInfo(filePath);

        //when
        PlainContentReader contentReader = new PlainContentReader(fileData);

        //then
        Assertions.assertThrows(SourceFileIsEmptyException.class, contentReader::readContent);
    }
}