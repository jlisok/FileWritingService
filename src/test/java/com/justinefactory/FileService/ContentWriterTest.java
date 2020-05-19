package com.justinefactory.FileService;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

import static com.justinefactory.FileService.CreateAndDeleteFilesBeforeAfterAll.createTemporaryDirectory;
import static com.justinefactory.FileService.CreateAndDeleteFilesBeforeAfterAll.removeAllDirs;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ContentWriterTest {

    static Path dir;
    @BeforeAll
    static void createDirsBeforeAll() throws Exception {
        dir = createTemporaryDirectory();
    }

    @AfterAll
    static void removeDirsAfterAll() throws Exception {
        removeAllDirs(dir);
    }

    @Test
    void write2FileWhenFileDoesNotExist() throws IOException, CouldNotWrite2FileAlreadyExists {
        Path filePath = dir.resolve("doc.csv");
        FileData file2writeData = new FileData(filePath);
        //System.out.println(dir.toString());
        int nLines = 12;
        Random newRandom = new Random();
        RandomIntegerGenerator newGenerator = new RandomIntegerGenerator(newRandom);
        ArrayList<Integer> newContent = newGenerator.generateContent(nLines);
        FileWriter newFileWriter = new FileWriter(file2writeData);
        newFileWriter.writeContent(newContent);
        assertTrue(Files.exists(filePath));
    }


    @Test
    void write2FileWhenFileExist() throws IOException {
        Path filePath = Files.createTempFile(dir, "doc", ".csv");
        FileData file2writeData = new FileData(filePath);
        int nLines = 5;
        Random newRandom = new Random();
        RandomIntegerGenerator newGenerator = new RandomIntegerGenerator(newRandom);
        ArrayList<Integer> newContent = newGenerator.generateContent(nLines);
        FileWriter newFileWriter = new FileWriter(file2writeData);
        Assertions.assertThrows(CouldNotWrite2FileAlreadyExists.class, () -> {
            newFileWriter.writeContent(newContent);
        });
    }
}