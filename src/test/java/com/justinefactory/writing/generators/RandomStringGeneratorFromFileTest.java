package com.justinefactory.writing.generators;

import com.justinefactory.domain.PathData;
import com.justinefactory.writing.domain.ContentStorage;
import com.justinefactory.writing.domain.TwoElemContent;
import com.justinefactory.writing.exceptions.FileWithStringsToGenerateContentIsEmptyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Random;

import static com.justinefactory.testutil.PathToResourcesGetter.getPathToResource;
import static com.justinefactory.util.CurrentTimStampWithPrecisionConversion.getCurrentTimeInNanoSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomStringGeneratorFromFileTest {

    @Test
    void generateStringContentWhenStringFileEmpty() throws Exception {
        //given
        Random newRandom = new Random();
        Path filePathRandomStrings = getPathToResource("empty-string-content.csv");

        //when
        PathData fileDataRanStr = new PathData(filePathRandomStrings);

        //then
        Assertions.assertThrows(FileWithStringsToGenerateContentIsEmptyException.class, () -> new RandomStringGeneratorFromFile(newRandom, fileDataRanStr));
    }

    @Test
    void generateStringContentWhen1String() throws Exception {
        //given
        Random newRandom = new Random();
        Path filePathRandomStrings = getPathToResource("string-content-only-1string.csv");
        PathData fileDataRanStr = new PathData(filePathRandomStrings);
        RandomStringGeneratorFromFile newGenerator = new RandomStringGeneratorFromFile(newRandom, fileDataRanStr);

        //when
        int nLines = 5;
        ContentStorage<TwoElemContent> newContent = newGenerator.generateContent(nLines);

        //then
        assertEquals(newContent.getContentSize(), nLines);
        for (int i = 0; i < newContent.getContentSize(); i++) {
            assertEquals(newContent.getContent(i).getRandomString(), "Jagulars");
        }
    }


    @Test
    void generateTimeStampContent() throws Exception {
        //given
        Random newRandom = new Random();
        Path filePathRandomStrings = getPathToResource("string-content-for-tests.csv");
        PathData fileDataRanStr = new PathData(filePathRandomStrings);
        RandomStringGeneratorFromFile newGenerator = new RandomStringGeneratorFromFile(newRandom, fileDataRanStr);

        //when
        Long timeStart = getCurrentTimeInNanoSeconds();
        int nLines = 3;
        ContentStorage<TwoElemContent> newContent = newGenerator.generateContent(nLines);
        Long timeStop = getCurrentTimeInNanoSeconds();

        //then
        assertEquals(newContent.getContentSize(), nLines);
        for (int i = 0; i < newContent.getContentSize(); i++) {
            assertTrue(newContent.getContent(i).getTimeStamp() >= timeStart && newContent.getContent(i).getTimeStamp() <= timeStop);
        }
    }

}