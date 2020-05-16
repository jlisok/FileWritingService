package com.justinefactory.FileService;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Collectors;

public class CsvFileWriter<T extends ContentParser & Serializable> implements ContentWriter<T> {

    private final Path filePath;

    CsvFileWriter(Path fp) {
        filePath = fp;
    }

    @Override
    public void writeContent(Collection<T> content) throws IOException, CouldNotWrite2FileAlreadyExists {

        if (checkIfFileAlreadyExists(filePath)) {
            System.out.println("Could not create a file. This file already exists.");
            throw new CouldNotWrite2FileAlreadyExists();
        }

        createNonExistingDirs(filePath);

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {


            for (T items : content) {
                String newStringLine = items.parseVars();
                writer.write(newStringLine);
                writer.newLine();
            }

        }

    }


    private void createNonExistingDirs(Path filePath) throws IOException {
        Files.createDirectories(filePath.getParent());
    }


    private boolean checkIfFileAlreadyExists(Path filePath) {
        return Files.exists(filePath);
    }

}
