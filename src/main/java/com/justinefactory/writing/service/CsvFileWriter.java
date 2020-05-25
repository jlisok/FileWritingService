package com.justinefactory.writing.service;

import com.justinefactory.domain.FileData;
import com.justinefactory.writing.service.exceptions.ContentWritingException;
import com.opencsv.CSVWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

class CsvFileWriter<T extends ContentToCsvLine<T>> implements ContentWriter<T> {

    private final FileData fileData;
    private static final Logger logger = LogManager.getLogger(CsvFileWriter.class);


    CsvFileWriter(FileData fp) {
        fileData = fp;
    }

    @Override
    public void writeContent(Collection<T> content) throws IOException, ContentWritingException {
        logger.debug("Writing content to CSV file id {}.", fileData.getFileId());

        if (checkIfFileAlreadyExists(fileData.getFilePath())) {
            logger.info("Could not create CSV file {}. File {} already exists.", fileData.getFileId(), fileData.getFilePath());
            throw new ContentWritingException("Could not create CSV file. File already exists.");
        }

        createNonExistingDirs(fileData.getFilePath());

        try (BufferedWriter writer = Files.newBufferedWriter(fileData.getFilePath());
             CSVWriter csvWriter = new CSVWriter(writer)
        ) {
            for (T items : content) {
                String[] newStringLine = items.varsToCsvLine();
                csvWriter.writeNext(newStringLine);
            }
            logger.info("CSV file {} has been created and appended successfully.", fileData.getFileId());
        }

    }


    private void createNonExistingDirs(Path fileData) throws IOException {
        Files.createDirectories(fileData.getParent());
    }


    private boolean checkIfFileAlreadyExists(Path fileData) {
        return Files.exists(fileData);
    }

}
