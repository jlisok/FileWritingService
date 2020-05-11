import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileContentGeneratorTest {

    @Test
    void generateRandomContent() {
        FileContentGenerator randomGenerator = new FileContentGenerator(5);
        ArrayList<Integer> content = randomGenerator.generateRandomContent();
        assertEquals(content.size(),5);
    }

    @Test
    void generateRandomContentWhen0() {
        FileContentGenerator randomGenerator = new FileContentGenerator(0);
        ArrayList<Integer> content = randomGenerator.generateRandomContent();
        assertEquals(content.size(),0);
    }


}