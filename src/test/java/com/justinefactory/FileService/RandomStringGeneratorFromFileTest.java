package com.justinefactory.FileService;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.justinefactory.testutil.PathToResourcesGetter.getPath;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomStringGeneratorFromFileTest {

    @Test
    void generateStringContentWhen1String() throws Exception {
        int nLines = 5;
        Random newRandom = new Random();
        Path filePathRanStrings = getPath("stringcontentonly1string.csv");
        FileData fileDataRanStr = new FileData(filePathRanStrings);
        RandomStringGeneratorFromFile newGenerator = new RandomStringGeneratorFromFile(newRandom, fileDataRanStr);
        ArrayList<TwoElemContent> newContent = newGenerator.generateContent(nLines);
        assertEquals(newContent.size(), nLines);
        for (TwoElemContent item : newContent) {
            assertEquals(item.getRandomString(), "Jagulars");
        }
    }


    @Test
    void generateTimeStampContent() throws Exception {
        Long timeStart = getCurrentTime();
        int nLines = 3;
        Random newRandom = new Random();
        Path filePathRanStrings = getPath("stringcontentfortests.csv");
        FileData fileDataRanStr = new FileData(filePathRanStrings);
        RandomStringGeneratorFromFile newGenerator = new RandomStringGeneratorFromFile(newRandom, fileDataRanStr);
        ArrayList<TwoElemContent> newContent = newGenerator.generateContent(nLines);
        Long timeStop = getCurrentTime();
        assertEquals(newContent.size(), nLines);
        for (TwoElemContent item : newContent) {
            assertTrue(item.getTimeStamp() >= timeStart && item.getTimeStamp() <= timeStop);
        }
    }


    private Long recalculateInstantToNanoSeconds(Instant tstmp) {
        return TimeUnit.SECONDS.toNanos(tstmp.getEpochSecond()) + tstmp.getNano();
    }

    private Long getCurrentTime(){
        Instant now = Instant.now();
        return recalculateInstantToNanoSeconds(now);
    }

}