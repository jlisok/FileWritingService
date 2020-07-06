package com.justinefactory.writing.writers.tofile;

import com.justinefactory.domain.PathInfo;
import com.justinefactory.writing.domain.JsonContent;
import com.justinefactory.writing.exceptions.ContentWritingException;
import com.justinefactory.writing.writers.ContentWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;

public class JsonFileWriter implements ContentWriter<JsonContent, PathInfo> {

    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void writeContent(JsonContent jsonContent, PathInfo pathInfo) throws ContentWritingException {
        try (BufferedWriter writer = Files.newBufferedWriter(pathInfo.getPath())) {
            writer.write(jsonContent.getContent());
        } catch (IOException e) {
            logger.warn("Problem while writing to file. Message: {}. Writing aborted.", e.getMessage());
            throw new ContentWritingException(e, "Problem while writing to file. Writing aborted.");
        }
    }
}
