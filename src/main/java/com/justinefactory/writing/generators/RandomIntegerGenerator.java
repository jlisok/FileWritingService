package com.justinefactory.writing.generators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Random;

public class RandomIntegerGenerator implements ContentGenerator<Integer> {

    private final Random newNumber;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public RandomIntegerGenerator(Random newNumber) {
        this.newNumber = newNumber;
    }

    @Override
    public ArrayList<Integer> generateContent(int nLines) {
        logger.debug("Generating random Integers.");
        ArrayList<Integer> randomContent = new ArrayList<>(nLines);
        for (int i = 0; i < nLines; i++) {
            randomContent.add(newNumber.nextInt());
        }
        logger.debug("{} random Integers have been generated successfully.", nLines);
        return randomContent;
    }

    @Override
    public ArrayList<Integer> generateContent() {
        logger.debug("Generating 1 random Integer.");
        ArrayList<Integer> randomContent = new ArrayList<>(1);
        randomContent.add(newNumber.nextInt());
        logger.debug("1 random Integer has been generated successfully.");
        return randomContent;
    }

}
