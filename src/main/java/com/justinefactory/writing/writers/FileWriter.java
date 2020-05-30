package com.justinefactory.writing.writers;

import com.justinefactory.domain.FileData;
import com.justinefactory.writing.exceptions.ContentWritingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

class FileWriter<T> implements ContentWriter<T> {

    private final FileData fileData;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    FileWriter(FileData fd) {
        fileData = fd;
    }


    @Override
    public void writeContent(Collection<T> content) throws IOException, ContentWritingException {

        if (checkIfFileAlreadyExists(fileData.getFilePath())) {
            logger.info("Could not create file {}. File {} already exists.", fileData.getFileId(), fileData.getFilePath());
            throw new ContentWritingException("Could not create file. File already exists.");
        }
        createNonExistingDirs(fileData.getFilePath());
        logger.info("Writing to File {}.", fileData.getFileId());

        try (BufferedWriter writer = Files.newBufferedWriter(fileData.getFilePath())) {
            for (T item : content) {
                writer.write(item.toString());
                writer.newLine();
            }
            logger.info("File {} has been created and written into successfully.", fileData.getFileId());
        }
    }


    private void createNonExistingDirs(Path fileData) throws IOException {
        Files.createDirectories(fileData.getParent());
    }


    private boolean checkIfFileAlreadyExists(Path fileData) {
        return Files.exists(fileData);
    }

}
