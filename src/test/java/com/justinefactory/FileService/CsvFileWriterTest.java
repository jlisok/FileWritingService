package com.justinefactory.FileService;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CsvFileWriterTest {

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
        Path filePath = dir.resolve("doc.csv");
        Path filePathRanStrings = Paths.get("C:/Users/justy/IdeaProjects/Writing2File/src/test/java/com/justinefactory/FileService/stringcontentfortests.csv");

        int nLines = 12;
        Random newRandom = new Random();

        RandomIntegerGenerator newIntGenerator = new RandomIntegerGenerator(newRandom);
        RandomStringGeneratorFromFile new2ElemGenerator = new RandomStringGeneratorFromFile(newRandom, filePathRanStrings);
        ThreeElementContentGenerator new3ElemGenerator = new ThreeElementContentGenerator(newIntGenerator, new2ElemGenerator);

        ArrayList<ThreeElemContent> threeElemContent = new3ElemGenerator.generateContent(nLines);

        CsvFileWriter newFileWriter = new CsvFileWriter(filePath);
        newFileWriter.writeContent(threeElemContent);

        assertTrue(Files.exists(filePath));

    }

    @Test
    void write2FileWhenFileExist() throws IOException {
        Path filePath = Files.createTempFile(dir, "doc", ".csv");
        Path filePathRanStrings = Paths.get("C:/Users/justy/IdeaProjects/Writing2File/src/test/java/com/justinefactory/FileService/stringcontentfortests.csv");
        int nLines = 5;
        Random newRandom = new Random();

        RandomIntegerGenerator newIntGenerator = new RandomIntegerGenerator(newRandom);
        RandomStringGeneratorFromFile new2ElemGenerator = new RandomStringGeneratorFromFile(newRandom, filePathRanStrings);
        ThreeElementContentGenerator new3ElemGenerator = new ThreeElementContentGenerator(newIntGenerator, new2ElemGenerator);

        ArrayList<ThreeElemContent> threeElemContent = new3ElemGenerator.generateContent(nLines);

        CsvFileWriter newFileWriter = new CsvFileWriter(filePath);

        Assertions.assertThrows(CouldNotWrite2FileAlreadyExists.class, () -> {
            newFileWriter.writeContent(threeElemContent);
        });

    }

}