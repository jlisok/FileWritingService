package com.justinefactory.reading.readers;

import com.justinefactory.domain.FileData;
import com.justinefactory.reading.exceptions.SourceFileIsEmptyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

import static com.justinefactory.testutil.PathToResourcesGetter.getPathToResource;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CsvContentReaderTest {

    @Test
    void readContentWhenFileMeetsConditions() throws Exception {
        //given
        Path filePath = getPathToResource("example-csv-file-for-tests.csv");
        //when
        FileData fileData = new FileData(filePath);
        CsvContentReader contentReader = new CsvContentReader(fileData);
        List<String[]> content = contentReader.readContent();
        //then
        assertFalse(content.isEmpty());
    }


    @Test
    void readContentWhenFileIsEmpty() throws Exception {
        //given
        Path filePath = getPathToResource("empty-string-content.csv");
        //when
        FileData fileData = new FileData(filePath);
        CsvContentReader contentReader = new CsvContentReader(fileData);
        //then
        Assertions.assertThrows(SourceFileIsEmptyException.class, () -> contentReader.readContent());
    }


}