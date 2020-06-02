package com.justinefactory.writing.writers;

import com.justinefactory.domain.FileData;
import com.justinefactory.writing.converters.ContentToCsvLineConverter;
import com.justinefactory.writing.exceptions.ContentWritingException;
import com.opencsv.CSVWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

class CsvFileWriter<T> implements ContentWriter<T> {

    private final FileData fileData;
    private final ContentToCsvLineConverter<T> converter;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    CsvFileWriter(FileData fp, ContentToCsvLineConverter<T> cr) {
        fileData = fp;
        converter = cr;
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
            for (T item : content) {
                String[] newStringLine = converter.varsToCsvLine(item);
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
