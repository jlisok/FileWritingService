package com.justinefactory.FileService;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RandomIntegerGeneratorTest {

    @Test
    void generateRandomContent() {
        //given
        Random newRandom = new Random();
        RandomIntegerGenerator newGenerator = new RandomIntegerGenerator(newRandom);

        //when
        int nLines = 5;
        ArrayList<Integer> newContent = newGenerator.generateContent(nLines);

        //then
        assertEquals(newContent.size(), nLines);
    }

    @Test
    void generateRandomContentWhen0() {
        //given
        Random newRandom = new Random();
        RandomIntegerGenerator randomGenerator = new RandomIntegerGenerator(newRandom);

        //when
        int nLines = 0;
        ArrayList<Integer> content = randomGenerator.generateContent(nLines);

        //then
        assertEquals(content.size(), 0);
    }
}