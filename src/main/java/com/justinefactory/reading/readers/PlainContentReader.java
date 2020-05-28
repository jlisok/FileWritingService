package com.justinefactory.reading.readers;

import com.justinefactory.domain.FileData;
import com.justinefactory.reading.exceptions.ContentReadingException;
import com.justinefactory.reading.exceptions.ReadingContentFromFileException;
import com.justinefactory.reading.exceptions.SourceFileIsEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.util.List;

import static com.justinefactory.util.FileNotEmptyAndExistChecker.checkIfFileExistsIsNotDirAndNotEmpty;

class PlainContentReader implements ContentReader<String> {

    private final FileData fileData;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    PlainContentReader(FileData fd) {
        fileData = fd;
    }


    @Override
    public List<String> readContent() throws ContentReadingException {
        logger.debug("Reading data from file id {}", fileData.getFileId());
        if (!checkIfFileExistsIsNotDirAndNotEmpty(fileData.getFilePath())) {
            logger.warn("Reading data from file id {} failed. File does not exist or is empty.", fileData.getFileId());
            throw new SourceFileIsEmptyException("Reading data from file id " + fileData.getFileId() + " - failed. File does not exist or is empty.");
        }

        try{
            logger.debug("Reading data from file id {} - success.", fileData.getFileId());
            return Files.readAllLines(fileData.getFilePath());
        } catch (Throwable e) {
            logger.warn("Reading data from file id {} failed. Message: {}", fileData.getFileId(), e.getMessage());
            throw new ReadingContentFromFileException(e, "Reading data from file id " + fileData.getFileId() + " - failed. Problem while reading all lines.");
        }
    }
}
