package com.justinefactory.reading.service;

import com.justinefactory.writing.service.CreateAndDeleteFilesBeforeAfterAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static com.justinefactory.testutil.PathToResourcesGetter.getPathToResource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static writing.service.util.FileNotEmptyAndExistController.assertFileExistsAndNotEmpty;

class FileNotEmptyAndExistControllerTest {


    @Test
    void assertFileExistsAndNotEmptyReturnsTrueWhenMeetingConditions() throws Exception {
        //when
        Path filePath = getPathToResource("exemplary-csv-file-for-tests.csv");
        //then
        assertTrue(assertFileExistsAndNotEmpty(filePath));
    }


    @Test
    void assertFileExistsAndNotEmptyReturnsTrueWhenEmpty() throws Exception {
        //when
        Path filePath = getPathToResource("empty-string-content.csv");
        //then
        assertFalse(assertFileExistsAndNotEmpty(filePath));
    }

    @Test
    void assertFileExistsAndNotEmptyReturnsTrueWhenDoesNotExist() throws Exception {
        //when
        Path dir = CreateAndDeleteFilesBeforeAfterAll.createTemporaryDirectory();
        //then
        assertFalse(assertFileExistsAndNotEmpty(dir));
    }

}