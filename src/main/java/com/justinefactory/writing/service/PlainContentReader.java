package com.justinefactory.writing.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static writing.service.util.FileNotEmptyAndExistController.assertFileExistsAndNotEmpty;

class PlainContentReader implements ContentReader<String> {

    private final FileData fileData;
    private static final Logger logger = LogManager.getLogger(PlainContentReader.class);


    PlainContentReader(FileData fd) {
        fileData = fd;
    }


    @Override
    public List<String> readContent() throws IOException, ReadingContentFromFileException {
        logger.debug("Reading data from file id {}", fileData.getFileId());
        if (!assertFileExistsAndNotEmpty(fileData.getFilePath())) {
            logger.warn("Reading data from file id {} failed. File does not exist or is empty.", fileData.getFileId());
            throw new SourceFileIsEmptyException("Reading data from file id " + fileData.getFileId() + " - failed. File does not exist or is empty.");
        }

        try{
            logger.debug("Reading data from file id {} - success.", fileData.getFileId());
            return Files.readAllLines(fileData.getFilePath());
        } catch (Throwable e) {
            logger.warn("Reading data from file id {} failed.", fileData.getFileId());
            throw new ReadingContentFromFileException(e, "Reading data from file id " + fileData.getFileId() + " - failed.");
        }
    }
}
