package FileService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;


class ContentWritingServiceTest {


    @Test
    void writeToFileWhenDoesNotExist() throws IOException, CouldNotWrite2FileAlreadyExists {
        Path dir = Files.createTempDirectory("TEST");
        Path filePath = Paths.get(dir.toString(),"doc.txt");
        int nLines = 7;
        Random newRandom = new Random();

        RandomIntegerGenerator newGenerator = new RandomIntegerGenerator(newRandom);
        FileWriter newFileWriter = new FileWriter(filePath);

        ContentWritingService newService = new ContentWritingService(newFileWriter,newGenerator);
        newService.processFile(nLines);

        Path dirFile = filePath.getParent();
        filePath.toFile().delete();
        Files.delete(dirFile);

    }



}