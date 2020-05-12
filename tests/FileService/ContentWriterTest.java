package FileService;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Random;


class ContentWriterTest {


    @Test
    void write2FileWhenFileDoesNotExist() throws IOException, CouldNotWrite2FileAlreadyExists {
        Path dir = Files.createTempDirectory("TEST");
        Path filePath = Paths.get(dir.toString(),"doc.txt");
        System.out.println(dir.toString());
        int nLines = 12;
        Random newRandom = new Random();

        RandomIntegerGenerator newGenerator = new RandomIntegerGenerator(newRandom);
        ArrayList<Integer> newContent = newGenerator.generateContent(nLines);

        FileWriter newFileWriter = new FileWriter(filePath);
        newFileWriter.writeContent(newContent);
        assertTrue(Files.exists(filePath));


        Path dirFile = filePath.getParent();
        filePath.toFile().delete();
        Files.delete(dirFile);

    }


    @Test
    void write2FileWhenFileExist() throws IOException, CouldNotWrite2FileAlreadyExists {
        Path filePath = Files.createTempFile("doc", ".txt");
        Path dir = filePath.getParent();
        System.out.println(dir.toString());
        int nLines = 5;
        Random newRandom = new Random();

        RandomIntegerGenerator newGenerator = new RandomIntegerGenerator(newRandom);
        ArrayList<Integer> newContent = newGenerator.generateContent(nLines);

        FileWriter newFileWriter = new FileWriter(filePath);
        Assertions.assertThrows(CouldNotWrite2FileAlreadyExists.class, () -> {
            newFileWriter.writeContent(newContent);
        });


        filePath.toFile().deleteOnExit();

    }



}