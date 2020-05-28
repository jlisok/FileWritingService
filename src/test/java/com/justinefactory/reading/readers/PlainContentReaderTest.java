package com.justinefactory.reading.readers;

import com.justinefactory.domain.FileData;
import com.justinefactory.reading.exceptions.SourceFileIsEmptyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static com.justinefactory.testutil.PathToResourcesGetter.getPathToResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlainContentReaderTest {

    @Test
    void readContentWhenFileContentMeetsConditions() throws Exception {
        //given
        FileData fileData = new FileData(getPathToResource("txt-file-content-integers.txt"));
        List<String> expectedContent = Arrays.asList("40000000", "800000", "3245", "2143567");

        //when
        PlainContentReader contentReader = new PlainContentReader(fileData);
        List<String> content = contentReader.readContent();

        //then
        assertEquals(content, expectedContent);

    }

    @Test
    void readContentWhenFileIsEmpty() throws Exception {
        //given
        Path filePath = getPathToResource("empty-string-content.csv");
        FileData fileData = new FileData(filePath);

        //when
        PlainContentReader contentReader = new PlainContentReader(fileData);

        //then
        Assertions.assertThrows(SourceFileIsEmptyException.class, () -> contentReader.readContent());
    }
}