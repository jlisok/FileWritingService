import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

class CouldNotWrite2FileAlreadyExists extends Exception{};

public class FileWritingService {

    final private int nLines;
    final private Path filePath;


    public FileWritingService(int nLines, String filePath) {
        this.nLines = nLines;
        this.filePath = Paths.get(filePath);
    }


    public void processFile() throws IOException, CouldNotWrite2FileAlreadyExists {

        FileContentGenerator randomGenerator = new FileContentGenerator(nLines);
        ArrayList<Integer> content = randomGenerator.generateRandomContent();

        if (checkIfFileAlreadyExists(filePath)) {
            System.out.println("Could not create a file. This file already exists.");
            throw new CouldNotWrite2FileAlreadyExists();
        }

        FileWriter newWriter = new FileWriter(content, filePath);
        newWriter.write2File();
        System.out.print("File has been created successfully");
    }



    boolean checkIfFileAlreadyExists(Path filePath) {
        return Files.exists(filePath);
    }


}
