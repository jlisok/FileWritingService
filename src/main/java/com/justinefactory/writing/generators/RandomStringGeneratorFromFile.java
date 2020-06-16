package com.justinefactory.writing.generators;

import com.justinefactory.domain.PathData;
import com.justinefactory.writing.domain.ContentStorage;
import com.justinefactory.writing.domain.TwoElemContent;
import com.justinefactory.writing.exceptions.ContentGeneratingException;
import com.justinefactory.writing.exceptions.ContentInitializationException;
import com.justinefactory.writing.exceptions.FileWithStringsToGenerateContentIsEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

import static com.justinefactory.util.CurrentTimStampWithPrecisionConversion.getCurrentTimeInNanoSeconds;

public class RandomStringGeneratorFromFile implements ContentGenerator<TwoElemContent> {

    private final Random newNumber;
    private final List<String> stringList;
    private final int stringListSize;
    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public RandomStringGeneratorFromFile(Random newNumber, PathData fileWithRandomStrings) throws ContentInitializationException {
        this.newNumber = newNumber;
        this.stringList = readStringsFromFile(fileWithRandomStrings);
        stringListSize = stringList.size();
    }


    @Override
    public ContentStorage<TwoElemContent> generateContent(int nLines) {
        logger.debug("Generating random strings from file.");
        ContentStorage<TwoElemContent> randomContent = new ContentStorage<>();

        for (int i = 0; i < nLines; i++) {

            // simulating a "long"-running operation
            try {
                Thread.sleep(0, new Random().nextInt(999));
            } catch (Exception e) {
                logger.warn("Could not execute program freeze.");
            }

            final Long timeStamp = getCurrentTimeInNanoSeconds();
            final int nString = newNumber.nextInt(stringListSize);
            TwoElemContent generatedData = new TwoElemContent(timeStamp, stringList.get(nString));
            randomContent.addContent(generatedData);
        }

        logger.debug("{} random strings have been created successfully.", nLines);
        return randomContent;
    }


    private List<String> readStringsFromFile(PathData stringFile) throws ContentInitializationException {
        logger.debug("Reading file id {} containing strings for random generator.", stringFile.getFileId());
        try {
            List<String> randomStrings = Files.readAllLines(stringFile.getFilePath());

            if (randomStrings.isEmpty()) {
                logger.warn("File id {} containing strings for random generator is empty.", stringFile.getFileId());
                throw new FileWithStringsToGenerateContentIsEmptyException("File containing strings for random generator is empty.");
            }

            logger.info("File id {} containing strings for random generator has been read successfully.", stringFile.getFileId());
            return randomStrings;
        } catch (IOException e) {
            logger.error("Could not read file id {} containing strings for random generator.", stringFile.getFileId());
            throw new ContentGeneratingException(e, "Could not read file containing strings for random generator.");
        }
    }
}
