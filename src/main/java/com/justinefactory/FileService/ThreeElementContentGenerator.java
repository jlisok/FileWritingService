package com.justinefactory.FileService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

class ThreeElementContentGenerator implements ContentGenerator<ThreeElemContent> {

    private final RandomIntegerGenerator intGenerator;
    private final RandomStringGeneratorFromFile strAndTStampGenerator;
    private static final Logger logger = LogManager.getLogger(ThreeElementContentGenerator.class);


    ThreeElementContentGenerator(RandomIntegerGenerator randomIntegerGenerator, RandomStringGeneratorFromFile randomStringAndTStampGenerator) {
        this.intGenerator = randomIntegerGenerator;
        this.strAndTStampGenerator = randomStringAndTStampGenerator;
    }


    @Override
    public ArrayList<ThreeElemContent> generateContent(int nLines) {
        logger.debug("Generating {} items of 3 element content.", nLines);
        ArrayList<Integer> randomInt = intGenerator.generateContent(nLines);
        ArrayList<TwoElemContent> randomStrTStamp = strAndTStampGenerator.generateContent(nLines);
        ArrayList<ThreeElemContent> randomContent = new ArrayList<>(nLines);

        for (int i = 0; i < nLines; i++) {
            ThreeElemContent threeContent = createThreeElemContentObject(randomInt.get(i), randomStrTStamp.get(i));
            randomContent.add(threeContent);
        }

        logger.debug("{} items of 3 element content have been created successfully.", nLines);
        return randomContent;
    }


    @Override
    public ArrayList<ThreeElemContent> generateContent() {
        logger.debug("Generating 1 item of 3 element content.");
        ArrayList<Integer> randomInt = intGenerator.generateContent(1);
        ArrayList<TwoElemContent> randomStrTStamp = strAndTStampGenerator.generateContent(1);
        ArrayList<ThreeElemContent> randomContent = new ArrayList<>(1);
        ThreeElemContent threeContent = createThreeElemContentObject(randomInt.get(0), randomStrTStamp.get(0));
        randomContent.add(threeContent);
        logger.debug("1 item of 3 element content has been created successfully.");
        return randomContent;
    }


    private ThreeElemContent createThreeElemContentObject(Integer rInt, TwoElemContent rTwoElem) {
        return new ThreeElemContent(rTwoElem.getTimeStamp(), rInt, rTwoElem.getRandomString());
    }

}
