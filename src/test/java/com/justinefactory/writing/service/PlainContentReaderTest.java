package com.justinefactory.writing.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.justinefactory.testutil.PathToResourcesGetter.getPathToResource;
import static org.junit.jupiter.api.Assertions.*;

class PlainContentReaderTest {

    @Test
    void readContentWhenFileContentMeetsConditions() throws Exception {
        //given
        FileData fileData = new FileData(getPathToResource("txt-file-content-integers.txt"));
        List<String> prepContent = new ArrayList<>();
        prepContent.add("40000000");
        prepContent.add("800000");
        prepContent.add("3245");
        prepContent.add("2143567");


        //when
        PlainContentReader contentReader = new PlainContentReader(fileData);
        List<String> content = contentReader.readContent();

        //then
        assertTrue(content.equals(prepContent));

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