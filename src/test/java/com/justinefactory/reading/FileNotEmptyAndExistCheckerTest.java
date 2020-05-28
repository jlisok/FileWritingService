package com.justinefactory.reading;

import com.justinefactory.reading.exceptions.ContentReadingException;
import com.justinefactory.testutil.CreateAndDeleteFilesBeforeAfterAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static com.justinefactory.testutil.PathToResourcesGetter.getPathToResource;
import static com.justinefactory.util.FileNotEmptyAndExistChecker.checkIfFileExistsIsNotDirAndNotEmpty;
import static org.junit.jupiter.api.Assertions.*;

class FileNotEmptyAndExistCheckerTest {

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
    void assertFileExistsAndNotEmptyReturnsTrueWhenMeetingConditions() throws Exception {
        //when
        Path filePath = getPathToResource("example-csv-file-for-tests.csv");
        //then
        assertTrue(checkIfFileExistsIsNotDirAndNotEmpty(filePath));
    }


    @Test
    void assertFileExistsAndNotEmptyReturnsFalseWhenEmpty() throws Exception {
        //when
        Path filePath = getPathToResource("empty-string-content.csv");
        //then

        assertFalse(checkIfFileExistsIsNotDirAndNotEmpty(filePath));
    }


    @Test
    void assertFileExistsAndNotEmptyReturnsFalseWhenDir() throws Exception {
        //when - @beforeAll created dir
        //then
        assertFalse(checkIfFileExistsIsNotDirAndNotEmpty(dir));
    }


    @Test
    void assertFileExistsAndNotEmptyReturnsErrorWhenDoesNotExist() throws Exception {
        //when
        Path filePath = dir.resolve("newFile.csv");
        //then
        assertThrows(ContentReadingException.class, () -> checkIfFileExistsIsNotDirAndNotEmpty(filePath));
    }

}