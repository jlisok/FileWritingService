package com.justinefactory.FileService;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.Collection;

class CouldNotWrite2FileAlreadyExists extends Exception {
};

class FileWriter implements ContentWriter<Integer> {

    private final Path filePath;

    FileWriter(Path fp) {
        filePath = fp;
    }

    @Override
    public void writeContent(Collection<Integer> content) throws IOException, CouldNotWrite2FileAlreadyExists {

        if (checkIfFileAlreadyExists(filePath)) {
            System.out.println("Could not create a file. This file already exists.");
            throw new CouldNotWrite2FileAlreadyExists();
        }

        createNonExistingDirs(filePath);

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {

            for (Integer items : content) {
                writer.write(items.toString());
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
