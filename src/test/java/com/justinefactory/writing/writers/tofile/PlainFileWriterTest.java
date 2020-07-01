package com.justinefactory.writing.writers.tofile;

import com.justinefactory.domain.PathInfo;
import com.justinefactory.testutil.CreateAndDeleteFilesBeforeAfterAll;
import com.justinefactory.writing.domain.PlainContent;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


class PlainFileWriterTest {

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
    void write2FileWhenWritingRandomInteger() throws Exception {
        //given
        Path filePath = dir.resolve("doc.csv");
        PathInfo file2writeData = new PathInfo(filePath);
        PlainContent readyToWriteContent = new PlainContent(List.of("1", "2"));

        //when
        PlainFileWriter writer = new PlainFileWriter();
        writer.writeContent(readyToWriteContent, file2writeData);

        //then
        assertTrue(Files.exists(filePath));
        assertTrue(Files.size(filePath) > 0);
    }


}