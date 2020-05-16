package com.justinefactory.FileService;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

class ThreeElementContentGenerator implements ContentGenerator<ThreeElemContent> {

    private final RandomIntegerGenerator intGenerator;
    private final RandomStringGeneratorFromFile strAndTStampGenerator;



    ThreeElementContentGenerator(RandomIntegerGenerator randomIntegerGenerator, RandomStringGeneratorFromFile randomStringAndTStampGenerator){
        this.intGenerator = randomIntegerGenerator;
        this.strAndTStampGenerator = randomStringAndTStampGenerator;

    }



    @Override
    public ArrayList<ThreeElemContent> generateContent(int nLines) throws IOException {

        ArrayList<Integer> randomInt = intGenerator.generateContent(nLines);
        ArrayList<TwoElemContent> randomStrTStamp = strAndTStampGenerator.generateContent(nLines);
        ArrayList<ThreeElemContent> randomContent = new ArrayList<>(nLines);

        for(int i = 0; i < nLines; i++){

            ThreeElemContent threeContent = createThreeElemContentObject(randomInt.get(i), randomStrTStamp.get(i));
            randomContent.add(threeContent);

        }

        return randomContent;
    }

    private ThreeElemContent createThreeElemContentObject(Integer rInt, TwoElemContent rTwoElem){

        ThreeElemContent threeContent = new ThreeElemContent(rTwoElem.getTimeStamp(), rInt, rTwoElem.getRandomString());
        return threeContent;

    }

}
