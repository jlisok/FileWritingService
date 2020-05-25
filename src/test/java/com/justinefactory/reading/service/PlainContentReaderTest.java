package com.justinefactory.reading.service;

import com.justinefactory.domain.FileData;
import com.justinefactory.reading.service.exceptions.SourceFileIsEmptyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.justinefactory.testutil.PathToResourcesGetter.getPathToResource;
import static org.junit.jupiter.api.Assertions.*;

class PlainContentReaderTest {

    @Test
    void readContentWhenFileContentMeetsConditions() throws Exception {
        //given
        FileData fileData = new FileData(getPathToResource("txt-file-content-integers.txt"));

        List<String> items = Arrays.asList("40000000", "800000", "3245", "2143567");
        List<String> expectedContent = new ArrayList<>();
        expectedContent.addAll(items);

        //when
        PlainContentReader contentReader = new PlainContentReader(fileData);
        List<String> content = contentReader.readContent();

        //then
        assertTrue(content.equals(expectedContent));

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