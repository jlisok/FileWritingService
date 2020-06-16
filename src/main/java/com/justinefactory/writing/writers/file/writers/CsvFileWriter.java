package com.justinefactory.writing.writers.file.writers;

import com.justinefactory.domain.PathData;
import com.justinefactory.writing.domain.ContentStorage;
import com.justinefactory.writing.exceptions.ContentWritingException;
import com.justinefactory.writing.writers.ContentWriter;
import com.opencsv.CSVWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;

public class CsvFileWriter implements ContentWriter<ContentStorage<String[]>> {

    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    @Override
    public void writeContent(ContentStorage<String[]> content, PathData fileData) throws ContentWritingException {
        try (BufferedWriter writer = Files.newBufferedWriter(fileData.getFilePath());
             CSVWriter csvWriter = new CSVWriter(writer)
        ) {
            for (String[] item : content.getAllContent()) {
                csvWriter.writeNext(item);
            }

        } catch (IOException e) {
            logger.warn("Problem while writing to file. Message: {}. Writing aborted.", e.getMessage());
            throw new ContentWritingException(e, "Problem while writing to file. Writing aborted.");
        }
    }
}
