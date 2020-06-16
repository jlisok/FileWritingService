package com.justinefactory.writing.generators;

import com.justinefactory.reading.exceptions.ContentStoringException;
import com.justinefactory.writing.domain.ContentStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.Random;

public class RandomIntegerGenerator implements ContentGenerator<Integer> {

    private final Random newNumber;
    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public RandomIntegerGenerator(Random newNumber) {
        this.newNumber = newNumber;
    }

    @Override
    public ContentStorage<Integer> generateContent(int nLines) throws ContentStoringException {
        logger.debug("Generating random Integers.");
        ContentStorage<Integer> randomContent = new ContentStorage<>();
        for (int i = 0; i < nLines; i++) {
            randomContent.addContent(newNumber.nextInt());
        }
        logger.debug("{} random Integers have been generated successfully.", nLines);
        return randomContent;
    }

}
