package com.justinefactory.writing.util;

import com.justinefactory.domain.PathData;
import com.justinefactory.testutil.CreateAndDeleteFilesBeforeAfterAll;
import com.justinefactory.writing.exceptions.ContentWritingException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CheckerIfFileAlreadyWrittenTest {

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
    void checkIfFileWrittenWhenFileDoesExist() throws Exception {
        //given
        Path file = Files.createTempFile(dir, "doc", ".csv");
        PathData filePath = new PathData(file);

        //when
        CheckerIfFileAlreadyWritten checker = new CheckerIfFileAlreadyWritten();

        //then
        assertThrows(ContentWritingException.class, () -> checker.assureNotExist(filePath));
    }


}