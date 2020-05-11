import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileWriterTest {

    @Test
    void write2FileWhenDirDoesNotExist() throws IOException {

        Path filePath = Paths.get("C:/tmp/JAVA/TEST/doc.txt"); // UWAGA NA ZMIANE SCIEZKI, KONIEC TESTU USUWA 2 POZIOMY
        FileContentGenerator newGenerator = new FileContentGenerator(2);
        ArrayList<Integer> newContent = newGenerator.generateRandomContent();
        FileWriter newFileWriter = new FileWriter(newContent,filePath);
        newFileWriter.write2File();
        assertTrue(Files.exists(filePath));

        filePath.toFile().deleteOnExit();
    }


}