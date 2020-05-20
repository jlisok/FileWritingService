package com.justinefactory.FileService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.justinefactory.testutil.PathToResourcesGetter.getPathToResource;
import static writing.service.util.CurrentTimStampWithPrecisionConversion.getCurrentTimeInNanoSeconds;

class RandomStringGeneratorFromFileTest {

    @Test
    void generateStringContentWhenStringFileEmpty() throws Exception {
        //given
        Random newRandom = new Random();
        Path filePathRanStrings = getPathToResource("emptystringcontent.csv");

        //when
        FileData fileDataRanStr = new FileData(filePathRanStrings);

        //then
        Assertions.assertThrows(SourceFileIsEmptyException.class, () -> new RandomStringGeneratorFromFile(newRandom, fileDataRanStr));
    }

    @Test
    void generateStringContentWhen1String() throws Exception {
        //given
        Random newRandom = new Random();
        Path filePathRanStrings = getPathToResource("stringcontentonly1string.csv");
        FileData fileDataRanStr = new FileData(filePathRanStrings);
        RandomStringGeneratorFromFile newGenerator = new RandomStringGeneratorFromFile(newRandom, fileDataRanStr);

        //when
        int nLines = 5;
        ArrayList<TwoElemContent> newContent = newGenerator.generateContent(nLines);

        //then
        assertEquals(newContent.size(), nLines);
        for (TwoElemContent item : newContent) {
            assertEquals(item.getRandomString(), "Jagulars");
        }
    }


    @Test
    void generateTimeStampContent() throws Exception {
        //given
        Random newRandom = new Random();
        Path filePathRanStrings = getPathToResource("stringcontentfortests.csv");
        FileData fileDataRanStr = new FileData(filePathRanStrings);
        RandomStringGeneratorFromFile newGenerator = new RandomStringGeneratorFromFile(newRandom, fileDataRanStr);

        //when
        Long timeStart = getCurrentTimeInNanoSeconds();
        int nLines = 3;
        ArrayList<TwoElemContent> newContent = newGenerator.generateContent(nLines);
        Long timeStop = getCurrentTimeInNanoSeconds();

        //then
        assertEquals(newContent.size(), nLines);
        for (TwoElemContent item : newContent) {
            assertTrue(item.getTimeStamp() >= timeStart && item.getTimeStamp() <= timeStop);
        }
    }

}