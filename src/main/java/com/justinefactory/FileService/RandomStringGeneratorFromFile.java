package com.justinefactory.FileService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

class RandomStringGeneratorFromFile implements ContentGenerator<TwoElemContent> {

    private final Random newNumber;
    private final List<String> stringList;
    private final int stringListSize;
    private static final Logger logger = LogManager.getLogger(RandomStringGeneratorFromFile.class);

    RandomStringGeneratorFromFile(Random newNumber, FileData fileWithRandomStrings) throws ContentGeneratingException {
        this.newNumber = newNumber;
        this.stringList = readStringsFromFile(fileWithRandomStrings);
        stringListSize = stringList.size();
    }


    @Override
    public ArrayList<TwoElemContent> generateContent(int nLines) {
        logger.debug("Initializing generating random strings from file.");
        ArrayList<TwoElemContent> randomContent = new ArrayList<>(nLines);
        for (int i = 0; i < nLines; i++) {
            // simulating a "long"-running operation
            try {
                Thread.sleep(0, new Random().nextInt(999));
            } catch (Exception e) {
                logger.warn("Could not execute program freeze.");
            }
            final Instant now = Instant.now();
            final Long timeStamp = recalculateInstantToNanoSeconds(now);
            final int nString = newNumber.nextInt(stringListSize);
            TwoElemContent generatedData = new TwoElemContent(timeStamp, stringList.get(nString));
            randomContent.add(generatedData);
        }
        logger.debug("{} random strings have been created successfully.", nLines);
        return randomContent;
    }


    @Override
    public ArrayList<TwoElemContent> generateContent() {
        logger.debug("Initializing generating random strings from file.");
        ArrayList<TwoElemContent> randomContent = new ArrayList<>(1);
        // simulating a "long"-running operation
        try {
            Thread.sleep(0, new Random().nextInt(999));
        } catch (Exception e) {
            logger.warn("Could not execute program freeze.");
        }
        final Instant now = Instant.now();
        final Long timeStamp = recalculateInstantToNanoSeconds(now);
        final int nString = newNumber.nextInt(stringListSize);
        TwoElemContent generatedData = new TwoElemContent(timeStamp, stringList.get(nString));
        randomContent.add(generatedData);
        logger.debug("1 random strings have been created successfully.");
        return randomContent;
    }


    private List<String> readStringsFromFile(FileData stringFile) throws ContentGeneratingException {
        logger.debug("Initializing reading file id {} containing strings for random generator.", stringFile.getFileId().toString());
        try {
            logger.info("File id {} containing strings for random generator has been read successfully.", stringFile.getFileId().toString());
            List<String> randomStrings = Files.readAllLines(stringFile.getFilePath());
            if (randomStrings.isEmpty()) {
                logger.warn("File id {} containing strings for random generator is empty.", stringFile.getFileId().toString());
            }
            return randomStrings;
        } catch (IOException e) {
            logger.error("Could not read file id {} containing strings for random generator.", stringFile.getFileId().toString());
            throw new ContentGeneratingException(e);
        }
    }

    private Long recalculateInstantToNanoSeconds(Instant tstmp) {
        return TimeUnit.SECONDS.toNanos(tstmp.getEpochSecond()) + tstmp.getNano();
    }


}
