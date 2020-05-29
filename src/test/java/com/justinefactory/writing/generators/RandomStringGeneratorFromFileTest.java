package com.justinefactory.writing.generators;

import com.justinefactory.domain.FileData;
import com.justinefactory.domain.TwoElemContent;
import com.justinefactory.writing.exceptions.FileWithStringsToGenerateContentIsEmptyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.justinefactory.testutil.PathToResourcesGetter.getPathToResource;
import static com.justinefactory.util.CurrentTimStampWithPrecisionConversion.getCurrentTimeInNanoSeconds;

class RandomStringGeneratorFromFileTest {

    @Test
    void generateStringContentWhenStringFileEmpty() throws Exception {
        //given
        Random newRandom = new Random();
        Path filePathRandomStrings = getPathToResource("empty-string-content.csv");

        //when
        FileData fileDataRanStr = new FileData(filePathRandomStrings);

        //then
        Assertions.assertThrows(FileWithStringsToGenerateContentIsEmptyException.class, () -> new RandomStringGeneratorFromFile(newRandom, fileDataRanStr));
    }

    @Test
    void generateStringContentWhen1String() throws Exception {
        //given
        Random newRandom = new Random();
        Path filePathRandomStrings = getPathToResource("string-content-only-1string.csv");
        FileData fileDataRanStr = new FileData(filePathRandomStrings);
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
        Path filePathRandomStrings = getPathToResource("string-content-for-tests.csv");
        FileData fileDataRanStr = new FileData(filePathRandomStrings);
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