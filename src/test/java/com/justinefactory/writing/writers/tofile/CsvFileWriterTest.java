package com.justinefactory.writing.writers.tofile;

import com.justinefactory.domain.PathInfo;
import com.justinefactory.testutil.CreateAndDeleteFilesBeforeAfterAll;
import com.justinefactory.writing.domain.CsvContent;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvFileWriterTest {

    static Path dir;

    @BeforeAll
    static void createDirsBeforeAll() throws Exception {
        dir = CreateAndDeleteFilesBeforeAfterAll.createTemporaryDirectory();
    }

    @AfterAll
    static void removeDirsAfterAll() throws Exception {
        CreateAndDeleteFilesBeforeAfterAll.removeAllDirs(dir);
    }


    @Test
    void write2FileWhenFileDoesNotExist() throws Exception {
        //given
        Path filePath = dir.resolve("doc.csv");
        PathInfo file2writeData = new PathInfo(filePath);
        CsvContent readyToWriteContent = new CsvContent(new String[]{"1590147349818750700", "1345882450", "Owl"});
        CsvFileWriter newFileWriter = new CsvFileWriter();

        //when
        newFileWriter.writeContent(readyToWriteContent, file2writeData);

        //then
        assertTrue(Files.exists(filePath));
        assertTrue(Files.size(filePath) > 0);
    }

}