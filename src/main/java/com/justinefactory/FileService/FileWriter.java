package com.justinefactory.FileService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

class CouldNotWrite2FileAlreadyExists extends Exception {
}

class FileWriter<T> implements ContentWriter<T> {

    private final FileData fileData;
    private static final Logger logger = LogManager.getLogger(FileWriter.class);


    FileWriter(FileData fd) {
        fileData = fd;
    }


    @Override
    public void writeContent(Collection<T> content) throws IOException, CouldNotWrite2FileAlreadyExists {

        if (checkIfFileAlreadyExists(fileData.getFilePath())) {
            logger.info("Could not create file {}. File {} already exists.", fileData.getFileId().toString(), fileData.getFilePath().toString());
            throw new CouldNotWrite2FileAlreadyExists();
        }
        createNonExistingDirs(fileData.getFilePath());
        logger.info("Initializing writing to File {}.", fileData.getFileId().toString());

        try (BufferedWriter writer = Files.newBufferedWriter(fileData.getFilePath())) {
            for (T items : content) {
                writer.write(items.toString());
                writer.newLine();
            }
            logger.info("File {} has been created and written into successfully.", fileData.getFileId().toString());
        }
    }


    private void createNonExistingDirs(Path fileData) throws IOException {
        Files.createDirectories(fileData.getParent());
    }


    private boolean checkIfFileAlreadyExists(Path fileData) {
        return Files.exists(fileData);
    }

}
