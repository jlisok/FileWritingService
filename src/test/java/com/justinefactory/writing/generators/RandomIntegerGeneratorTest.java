package com.justinefactory.writing.generators;

import com.justinefactory.writing.domain.Content;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RandomIntegerGeneratorTest {

    @Test
    void generateRandomContent() {
        //given
        Random newRandom = new Random();
        RandomIntegerGenerator newGenerator = new RandomIntegerGenerator(newRandom);

        //when
        int nLines = 5;
        Content<Integer> newContent = newGenerator.generateContent(nLines);

        //then
        assertEquals(newContent.getContentSize(), nLines);
    }

    @Test
    void generateRandomContentWhen0() {
        //given
        Random newRandom = new Random();
        RandomIntegerGenerator randomGenerator = new RandomIntegerGenerator(newRandom);

        //when
        int nLines = 0;
        Content<Integer> content = randomGenerator.generateContent(nLines);

        //then
        assertEquals(content.getContentSize(), 0);
    }
}