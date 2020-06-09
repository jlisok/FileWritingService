package com.justinefactory.writing.generators;

import com.justinefactory.domain.ThreeElemContent;
import com.justinefactory.writing.domain.ContentStorage;
import com.justinefactory.writing.domain.TwoElemContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class ThreeElementContentGenerator implements ContentGenerator<ThreeElemContent> {

    private final RandomIntegerGenerator intGenerator;
    private final RandomStringGeneratorFromFile strAndTStampGenerator;
    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    public ThreeElementContentGenerator(RandomIntegerGenerator randomIntegerGenerator, RandomStringGeneratorFromFile randomStringAndTStampGenerator) {
        this.intGenerator = randomIntegerGenerator;
        this.strAndTStampGenerator = randomStringAndTStampGenerator;
    }


    @Override
    public ContentStorage<ThreeElemContent> generateContent(int nLines) {
        logger.debug("Generating {} items of 3 element content.", nLines);
        ContentStorage<Integer> randomInt = intGenerator.generateContent(nLines);
        ContentStorage<TwoElemContent> randomStrTStamp = strAndTStampGenerator.generateContent(nLines);
        ContentStorage<ThreeElemContent> randomContent = new ContentStorage<>(nLines);

        for (int i = 0; i < nLines; i++) {
            ThreeElemContent threeContent = createThreeElemContentObject(randomInt.getContent(i), randomStrTStamp.getContent(i));
            randomContent.addContent(threeContent);
        }

        logger.debug("{} items of 3 element content have been created successfully.", nLines);
        return randomContent;
    }


    private ThreeElemContent createThreeElemContentObject(Integer rInt, TwoElemContent rTwoElem) {
        return new ThreeElemContent(rTwoElem.getTimeStamp(), rInt, rTwoElem.getRandomString());
    }

}
