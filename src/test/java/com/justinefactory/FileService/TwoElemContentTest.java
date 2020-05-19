package com.justinefactory.FileService;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

import static com.justinefactory.testutil.PathToResourcesGetter.getPath;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TwoElemContentTest {

    @Test
    void parseVarsToString() throws Exception {
        int nLines = 10;
        Random newRandom = new Random();
        Path filePathRanStrings = getPath("stringcontentfortests.csv");
        FileData fileDataRanStr = new FileData(filePathRanStrings);

        RandomStringGeneratorFromFile newGenerator = new RandomStringGeneratorFromFile(newRandom, fileDataRanStr);
        ArrayList<TwoElemContent> newContent = newGenerator.generateContent(nLines);

        for (TwoElemContent item : newContent) {
            String[] str = item.varsToCsvLine();
            assertTrue(str.length > 0);
            assertTrue(str[0].contains(item.getTimeStamp().toString()));
            assertTrue(str[1].contains(item.getRandomString()));
        }
    }

}