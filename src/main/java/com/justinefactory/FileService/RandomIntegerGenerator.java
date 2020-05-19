package com.justinefactory.FileService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Random;

class RandomIntegerGenerator implements ContentGenerator<Integer> {

    private Random newNumber;
    private static final Logger logger = LogManager.getLogger(RandomIntegerGenerator.class);

    RandomIntegerGenerator(Random newNumber) {
        this.newNumber = newNumber;
    }

    @Override
    public ArrayList<Integer> generateContent(int nLines) {
        logger.debug("Initializing generating random Integers.");
        ArrayList<Integer> randomContent = new ArrayList<>(nLines);
        for (int i = 0; i < nLines; i++) {
            randomContent.add(newNumber.nextInt());
        }
        logger.debug("{} random Integers have been generated successfully.", nLines);
        return randomContent;
    }

    @Override
    public ArrayList<Integer> generateContent() {
        logger.debug("Initializing generating 1 random Integer.");
        ArrayList<Integer> randomContent = new ArrayList<>(1);
        randomContent.add(newNumber.nextInt());
        logger.debug("Random Integer has been generated successfully.");
        return randomContent;
    }

}
