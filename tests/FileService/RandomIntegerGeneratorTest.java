package FileService;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RandomIntegerGeneratorTest {

    @Test
    void generateRandomContent() {
        int nLines = 5;
        Random newRandom = new Random();

        RandomIntegerGenerator newGenerator = new RandomIntegerGenerator(newRandom);
        ArrayList<Integer> newContent = newGenerator.generateContent(nLines);
        assertEquals(newContent.size(),5);
    }

    @Test
    void generateRandomContentWhen0() {
        int nLines = 0;
        Random newRandom = new Random();

        RandomIntegerGenerator randomGenerator = new RandomIntegerGenerator(newRandom);
        ArrayList<Integer> content = randomGenerator.generateContent(nLines);
        assertEquals(content.size(),0);
    }


}