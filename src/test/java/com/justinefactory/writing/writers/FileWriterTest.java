package com.justinefactory.writing.writers;

import com.justinefactory.domain.FileData;
import com.justinefactory.testutil.CreateAndDeleteFilesBeforeAfterAll;
import com.justinefactory.writing.exceptions.ContentWritingException;
import com.justinefactory.writing.generators.RandomIntegerGenerator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;


class FileWriterTest {

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
    void write2FileWhenFileDoesNotExist() throws IOException, ContentWritingException {
        //given
        Path filePath = dir.resolve("doc.csv");
        FileData file2writeData = new FileData(filePath);
        Random newRandom = new Random();
        RandomIntegerGenerator newGenerator = new RandomIntegerGenerator(newRandom);

        //when
        int nLines = 12;
        ArrayList<Integer> newContent = newGenerator.generateContent(nLines);
        FileWriter newFileWriter = new FileWriter(file2writeData);
        newFileWriter.writeContent(newContent);

        //then
        assertTrue(Files.exists(filePath));
        assertTrue(Files.size(filePath) > 0);
    }


    @Test
    void write2FileWhenFileExists() throws IOException {
        //given
        Path filePath = Files.createTempFile(dir, "doc", ".csv");
        FileData file2writeData = new FileData(filePath);
        Random newRandom = new Random();
        RandomIntegerGenerator newGenerator = new RandomIntegerGenerator(newRandom);
        FileWriter newFileWriter = new FileWriter(file2writeData);

        //when
        int nLines = 5;
        ArrayList<Integer> newContent = newGenerator.generateContent(nLines);

        //then
        Assertions.assertThrows(ContentWritingException.class, () -> {
            newFileWriter.writeContent(newContent);
        });
    }
}