package com.justinefactory.writing.writers.tofile;

import com.justinefactory.domain.PathInfo;
import com.justinefactory.writing.domain.ContentReadyForPlainWriter;
import com.justinefactory.writing.exceptions.ContentWritingException;
import com.justinefactory.writing.writers.ContentWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;

public class PlainFileWriter implements ContentWriter<ContentReadyForPlainWriter, PathInfo> {

    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void writeContent(ContentReadyForPlainWriter content, PathInfo fileData) throws ContentWritingException {
        try (BufferedWriter writer = Files.newBufferedWriter(fileData.getPath())) {
            for (String item : content.getContent()) {
                writer.write(item);
                writer.newLine();
            }
        } catch (IOException e) {
            logger.warn("Problem while writing to file. Message: {}. Writing aborted.", e.getMessage());
            throw new ContentWritingException(e, "Problem while writing to file. Writing aborted.");
        }
    }
}
