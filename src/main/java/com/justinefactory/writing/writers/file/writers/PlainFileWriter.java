package com.justinefactory.writing.writers.file.writers;

import com.justinefactory.domain.PathData;
import com.justinefactory.writing.domain.ContentStorage;
import com.justinefactory.writing.exceptions.ContentWritingException;
import com.justinefactory.writing.writers.ContentWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;

public class PlainFileWriter implements ContentWriter<ContentStorage<String>> {

    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void writeContent(ContentStorage<String> content, PathData fileData) throws ContentWritingException {
        try (BufferedWriter writer = Files.newBufferedWriter(fileData.getFilePath())) {
            for (String item : content.getAllContent()) {
                writer.write(item);
                writer.newLine();
            }
        } catch (IOException e) {
            logger.warn("Problem while writing to file. Message: {}. Writing aborted.", e.getMessage());
            throw new ContentWritingException(e, "Problem while writing to file. Message: " + e.getMessage() + " Writing aborted.");
        }
    }
}
