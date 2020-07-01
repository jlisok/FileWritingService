package com.justinefactory.writing.writers.tofile;

import com.justinefactory.domain.PathInfo;
import com.justinefactory.writing.domain.CsvContent;
import com.justinefactory.writing.exceptions.ContentWritingException;
import com.justinefactory.writing.writers.ContentWriter;
import com.opencsv.CSVWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;

public class CsvFileWriter implements ContentWriter<CsvContent, PathInfo> {

    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    @Override
    public void writeContent(CsvContent content, PathInfo fileData) throws ContentWritingException {
        try (BufferedWriter writer = Files.newBufferedWriter(fileData.getPath());
             CSVWriter csvWriter = new CSVWriter(writer)
        ) {
            for (String[] item : content.getContent()) {
                csvWriter.writeNext(item);
            }

        } catch (IOException e) {
            logger.warn("Problem while writing to file. Message: {}. Writing aborted.", e.getMessage());
            throw new ContentWritingException(e, "Problem while writing to file. Writing aborted.");
        }
    }
}
