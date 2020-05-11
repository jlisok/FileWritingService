import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

class FileWritingServiceTest {

    @Test
    void writeToFileWhenDoesNotExist() throws IOException, CouldNotWrite2FileAlreadyExists {
        FileWritingService newService = new FileWritingService(10, "C:/tmp/JAVA/TEST/doc.txt");
        newService.processFile();

    }

    @Test
    void writeToFileWhenExists() throws IOException, CouldNotWrite2FileAlreadyExists {
        FileWritingService newService = new FileWritingService(10, "C:/tmp/JAVA/TEST/doc.txt");


        Assertions.assertThrows(CouldNotWrite2FileAlreadyExists.class, () -> {
            newService.processFile();
        });

        Path filePath = Paths.get("C:/tmp/JAVA/TEST/doc.txt");
        filePath.toFile().deleteOnExit();

    }



}