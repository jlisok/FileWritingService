package com.justinefactory.writing.generators;

import com.justinefactory.domain.PathInfo;
import com.justinefactory.domain.ThreeElemContent;
import com.justinefactory.writing.domain.Content;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Random;

import static com.justinefactory.testutil.PathToResourcesGetter.getPathToResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreeElementContentGeneratorTest {

    @Test
    void generateContentWhenRandom() throws Exception {
        //given
        Path filePathRanStrings = getPathToResource("string-content-for-tests.csv");
        PathInfo fileDataRanStr = new PathInfo(filePathRanStrings);
        Random newRandom = new Random();
        RandomIntegerGenerator newIntGenerator = new RandomIntegerGenerator(newRandom);
        RandomStringGeneratorFromFile new2ElemGenerator = new RandomStringGeneratorFromFile(newRandom, fileDataRanStr);
        ThreeElementContentGenerator new3ElemGenerator = new ThreeElementContentGenerator(newIntGenerator, new2ElemGenerator);

        //when
        int nLines = 12;
        Content<ThreeElemContent> new3ElemContent = new3ElemGenerator.generateContent(nLines);

        //then
        assertEquals(new3ElemContent.getContentSize(), nLines);
    }

}