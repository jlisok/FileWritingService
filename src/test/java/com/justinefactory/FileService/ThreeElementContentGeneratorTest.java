package com.justinefactory.FileService;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

import static com.justinefactory.testutil.PathToResourcesGetter.getPath;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreeElementContentGeneratorTest {

    @Test
    void generateContentWhenRandom() throws Exception {
        Path filePathRanStrings = getPath("stringcontentfortests.csv");
        FileData fileDataRanStr = new FileData(filePathRanStrings);
        int nLines = 12;
        Random newRandom = new Random();
        RandomIntegerGenerator newIntGenerator = new RandomIntegerGenerator(newRandom);
        RandomStringGeneratorFromFile new2ElemGenerator = new RandomStringGeneratorFromFile(newRandom, fileDataRanStr);
        ThreeElementContentGenerator new3ElemGenerator = new ThreeElementContentGenerator(newIntGenerator, new2ElemGenerator);
        ArrayList<ThreeElemContent> new3ElemContent = new3ElemGenerator.generateContent(nLines);
        assertEquals(new3ElemContent.size(), nLines);
    }

}