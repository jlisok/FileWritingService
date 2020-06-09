package com.justinefactory.reading.readers;

import com.justinefactory.domain.PathData;
import com.justinefactory.reading.exceptions.SourceFileIsEmptyException;
import com.justinefactory.writing.domain.ContentStorage;
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
        PathData fileData = new PathData(getPathToResource("txt-file-content-integers.txt"));
        ContentStorage<String> expectedContent = new ContentStorage<>(Arrays.asList("40000000", "800000", "3245", "2143567"));

        //when
        PlainContentReader contentReader = new PlainContentReader(fileData);
        ContentStorage<String> content = contentReader.readContent();

        //then
        assertEquals(content, expectedContent);

    }

    @Test
    void readContentWhenFileIsEmpty() throws Exception {
        //given
        Path filePath = getPathToResource("empty-string-content.csv");
        PathData fileData = new PathData(filePath);

        //when
        PlainContentReader contentReader = new PlainContentReader(fileData);

        //then
        Assertions.assertThrows(SourceFileIsEmptyException.class, contentReader::readContent);
    }
}