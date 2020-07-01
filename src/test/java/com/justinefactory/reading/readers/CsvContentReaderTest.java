package com.justinefactory.reading.readers;

import com.justinefactory.domain.PathInfo;
import com.justinefactory.reading.exceptions.SourceFileIsEmptyException;
import com.justinefactory.writing.domain.CsvContent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static com.justinefactory.testutil.PathToResourcesGetter.getPathToResource;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CsvContentReaderTest {

    @Test
    void readContentWhenFileMeetsConditions() throws Exception {
        //given
        Path filePath = getPathToResource("example-csv-file-for-tests.csv");
        //when
        PathInfo fileData = new PathInfo(filePath);
        CsvContentReader contentReader = new CsvContentReader(fileData);
        CsvContent content = contentReader.readContent();
        //then
        assertNotEquals(0, content.size());
    }


    @Test
    void readContentWhenFileIsEmpty() throws Exception {
        //given
        Path filePath = getPathToResource("empty-string-content.csv");
        //when
        PathInfo fileData = new PathInfo(filePath);
        CsvContentReader contentReader = new CsvContentReader(fileData);
        //then
        Assertions.assertThrows(SourceFileIsEmptyException.class, contentReader::readContent);
    }


}