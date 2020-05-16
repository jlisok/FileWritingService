package com.justinefactory.FileService;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;


class ContentWriterTest {

    static Path dir;

    @BeforeAll
    static void createTemporaryDirectory() throws IOException {
        dir = Files.createTempDirectory("TEST");
    }


    @AfterAll
    static void removeAllDirs() throws IOException {
        Files.walk(dir)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }



    @Test
    void write2FileWhenFileDoesNotExist() throws IOException, CouldNotWrite2FileAlreadyExists {
        Path filePath = dir.resolve("doc.txt");
        int nLines = 12;
        Random newRandom = new Random();

        RandomIntegerGenerator newGenerator = new RandomIntegerGenerator(newRandom);
        ArrayList<Integer> newContent = newGenerator.generateContent(nLines);

        FileWriter newFileWriter = new FileWriter(filePath);
        newFileWriter.writeContent(newContent);
        assertTrue(Files.exists(filePath));

    }


    @Test
    void write2FileWhenFileExist() throws IOException {
        Path filePath = Files.createTempFile(dir, "doc", ".txt");
        int nLines = 5;
        Random newRandom = new Random();

        RandomIntegerGenerator newGenerator = new RandomIntegerGenerator(newRandom);
        ArrayList<Integer> newContent = newGenerator.generateContent(nLines);

        FileWriter newFileWriter = new FileWriter(filePath);
        Assertions.assertThrows(CouldNotWrite2FileAlreadyExists.class, () -> {
            newFileWriter.writeContent(newContent);
        });


        filePath.toFile().deleteOnExit();

    }


}