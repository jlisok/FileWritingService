package com.justinefactory.FileService;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RandomStringGeneratorFromFileTest {

    @Test
    void generateStringContentWhen1String() throws IOException {
        int nLines = 5;
        Random newRandom = new Random();
        Path filePath = Paths.get("C:/Users/justy/IdeaProjects/Writing2File/src/test/java/com/justinefactory/FileService/stringcontentonly1string.csv");

        RandomStringGeneratorFromFile newGenerator = new RandomStringGeneratorFromFile(newRandom, filePath);
        ArrayList<TwoElemContent> newContent = newGenerator.generateContent(nLines);

        assertEquals(newContent.size(), nLines);
        assertEquals(newContent.get(1).getRandomString(), "Jagulars");
    }

    @Test
    void generateStringContentWHenMultipleChoices() throws IOException {
        int nLines = 25;
        Random newRandom = new Random();
        Path filePath = Paths.get("C:/Users/justy/IdeaProjects/Writing2File/src/test/java/com/justinefactory/FileService/stringcontentfortests.csv");

        RandomStringGeneratorFromFile newGenerator = new RandomStringGeneratorFromFile(newRandom, filePath);
        ArrayList<TwoElemContent> newContent = newGenerator.generateContent(nLines);

        assertEquals(newContent.size(), nLines);

        boolean notEqual = false;
        for (int i = 0; i < nLines - 1; i++) {
            if (newContent.get(i).getRandomString() != newContent.get(i+1).getRandomString()) {
                notEqual = true;
                return;
            }
        }

        assertTrue(notEqual);
    }


    @Test
    void generateTimeStampContent() throws IOException {
        int nLines = 3;
        Random newRandom = new Random();
        Path filePath = Paths.get("C:/Users/justy/IdeaProjects/Writing2File/src/test/java/com/justinefactory/FileService/stringcontentonly1string.csv");

        RandomStringGeneratorFromFile newGenerator = new RandomStringGeneratorFromFile(newRandom, filePath);
        ArrayList<TwoElemContent> newContent = newGenerator.generateContent(nLines);

        assertEquals(newContent.size(), nLines);
        assertTrue(newContent.get(2).getTimeStamp() >= newContent.get(0).getTimeStamp());
    }


    @Test
    void parseVarsToString() throws IOException {
        int nLines = 10;
        Random newRandom = new Random();
        Path filePath = Paths.get("C:/Users/justy/IdeaProjects/Writing2File/src/test/java/com/justinefactory/FileService/stringcontentonly1string.csv");

        RandomStringGeneratorFromFile newGenerator = new RandomStringGeneratorFromFile(newRandom, filePath);
        ArrayList<TwoElemContent> newContent = newGenerator.generateContent(nLines);

        String str = newContent.get(9).parseVars();
        assertNotNull(str);
    }


}