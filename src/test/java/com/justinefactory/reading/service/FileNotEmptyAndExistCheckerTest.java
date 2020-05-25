package com.justinefactory.reading.service;

import com.justinefactory.reading.service.exceptions.ContentReadingException;
import com.justinefactory.testutil.CreateAndDeleteFilesBeforeAfterAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static com.justinefactory.testutil.PathToResourcesGetter.getPathToResource;
import static com.justinefactory.util.FileNotEmptyAndExistChecker.checkIfFileExistsIsNotDirAndNotEmpty;
import static org.junit.jupiter.api.Assertions.*;

class FileNotEmptyAndExistCheckerTest {


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
        //when
        Path dir = CreateAndDeleteFilesBeforeAfterAll.createTemporaryDirectory();
        //then

        assertFalse(checkIfFileExistsIsNotDirAndNotEmpty(dir));
    }


    @Test
    void assertFileExistsAndNotEmptyReturnsErrorWhenDoesNotExist() throws Exception {
        //when
        Path dir = CreateAndDeleteFilesBeforeAfterAll.createTemporaryDirectory();
        Path filePath = dir.resolve("newFile.csv");
        //then
        assertThrows(ContentReadingException.class, () -> checkIfFileExistsIsNotDirAndNotEmpty(filePath));
    }

}