package com.justinefactory.FileService;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ThreeElementContentGeneratorTest {

    @Test
    void generateContentWhenRandom() throws IOException {

        Path filePathRanStrings = Paths.get("C:/Users/justy/IdeaProjects/Writing2File/src/test/java/com/justinefactory/FileService/stringcontentfortests.csv");

        int nLines = 12;
        Random newRandom = new Random();

        RandomIntegerGenerator newIntGenerator = new RandomIntegerGenerator(newRandom);
        RandomStringGeneratorFromFile new2ElemGenerator = new RandomStringGeneratorFromFile(newRandom, filePathRanStrings);
        ThreeElementContentGenerator new3ElemGenerator = new ThreeElementContentGenerator(newIntGenerator, new2ElemGenerator);

        ArrayList<ThreeElemContent> new3ElemContent = new3ElemGenerator.generateContent(nLines);

        assertEquals(new3ElemContent.size(),nLines);

    }


    @Test
    void parseVarsToString() throws IOException {
        Path filePathRanStrings = Paths.get("C:/Users/justy/IdeaProjects/Writing2File/src/test/java/com/justinefactory/FileService/stringcontentfortests.csv");

        int nLines = 12;
        Random newRandom = new Random();

        RandomIntegerGenerator newIntGenerator = new RandomIntegerGenerator(newRandom);
        RandomStringGeneratorFromFile new2ElemGenerator = new RandomStringGeneratorFromFile(newRandom, filePathRanStrings);
        ThreeElementContentGenerator new3ElemGenerator = new ThreeElementContentGenerator(newIntGenerator, new2ElemGenerator);

        ArrayList<ThreeElemContent> new3ElemContent = new3ElemGenerator.generateContent(nLines);

        String str = new3ElemContent.get(9).parseVars();
        assertNotNull(str);
    }


}