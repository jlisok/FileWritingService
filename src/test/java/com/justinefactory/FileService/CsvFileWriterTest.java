package com.justinefactory.FileService;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

import static com.justinefactory.FileService.CreateAndDeleteFilesBeforeAfterAll.createTemporaryDirectory;
import static com.justinefactory.FileService.CreateAndDeleteFilesBeforeAfterAll.removeAllDirs;
import static com.justinefactory.testutil.PathToResourcesGetter.getPath;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvFileWriterTest {

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
    void write2FileWhenFileDoesNotExist() throws Exception {
        Path filePath = dir.resolve("doc.csv");
        Path filePathRanStrings = getPath("stringcontentfortests.csv");
        FileData fileDataRanStr = new FileData(filePathRanStrings);
        FileData file2writeData = new FileData(filePath);
        int nLines = 12;
        Random newRandom = new Random();
        RandomIntegerGenerator newIntGenerator = new RandomIntegerGenerator(newRandom);
        RandomStringGeneratorFromFile new2ElemGenerator = new RandomStringGeneratorFromFile(newRandom, fileDataRanStr);
        ThreeElementContentGenerator new3ElemGenerator = new ThreeElementContentGenerator(newIntGenerator, new2ElemGenerator);
        ArrayList<ThreeElemContent> threeElemContent = new3ElemGenerator.generateContent(nLines);
        CsvFileWriter newFileWriter = new CsvFileWriter(file2writeData);
        newFileWriter.writeContent(threeElemContent);
        assertTrue(Files.exists(filePath));
        assertTrue(Files.size(filePath) > 0);
    }

    @Test
    void write2FileWhenFileExist() throws Exception {
        Path filePath = Files.createTempFile(dir, "doc", ".csv");
        Path filePathRanStrings = getPath("stringcontentfortests.csv");
        FileData fileDataRanStr = new FileData(filePathRanStrings);
        FileData file2writeData = new FileData(filePath);
        int nLines = 5;
        Random newRandom = new Random();
        RandomIntegerGenerator newIntGenerator = new RandomIntegerGenerator(newRandom);
        RandomStringGeneratorFromFile new2ElemGenerator = new RandomStringGeneratorFromFile(newRandom, fileDataRanStr);
        ThreeElementContentGenerator new3ElemGenerator = new ThreeElementContentGenerator(newIntGenerator, new2ElemGenerator);
        ArrayList<ThreeElemContent> threeElemContent = new3ElemGenerator.generateContent(nLines);
        CsvFileWriter newFileWriter = new CsvFileWriter(file2writeData);
        Assertions.assertThrows(CouldNotWrite2FileAlreadyExists.class, () -> {
            newFileWriter.writeContent(threeElemContent);
        });
    }
}