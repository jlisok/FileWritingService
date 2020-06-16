package com.justinefactory.writing.generators;

import com.justinefactory.reading.exceptions.ContentStoringException;
import com.justinefactory.writing.domain.ContentStorage;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RandomIntegerGeneratorTest {

    @Test
    void generateRandomContent() throws ContentStoringException {
        //given
        Random newRandom = new Random();
        RandomIntegerGenerator newGenerator = new RandomIntegerGenerator(newRandom);

        //when
        int nLines = 5;
        ContentStorage<Integer> newContent = newGenerator.generateContent(nLines);

        //then
        assertEquals(newContent.getContentSize(), nLines);
    }

    @Test
    void generateRandomContentWhen0() throws ContentStoringException {
        //given
        Random newRandom = new Random();
        RandomIntegerGenerator randomGenerator = new RandomIntegerGenerator(newRandom);

        //when
        int nLines = 0;
        ContentStorage<Integer> content = randomGenerator.generateContent(nLines);

        //then
        assertEquals(content.getContentSize(), 0);
    }
}