package com.justinefactory.reading.readers;

import com.justinefactory.domain.PathData;
import com.justinefactory.reading.exceptions.ContentReadingException;
import com.justinefactory.reading.exceptions.ReadingContentFromFileException;
import com.justinefactory.reading.exceptions.SourceFileIsEmptyException;
import com.justinefactory.writing.domain.ContentStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.nio.file.Files;

import static com.justinefactory.util.FileNotEmptyAndExistChecker.checkIfFileExistsIsNotDirAndNotEmpty;

class PlainContentReader implements ContentReader<String> {

    private final PathData fileData;
    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    PlainContentReader(PathData fd) {
        fileData = fd;
    }


    @Override
    public ContentStorage<String> readContent() throws ContentReadingException {
        logger.debug("Reading data from file id {}", fileData.getFileId());
        if (!checkIfFileExistsIsNotDirAndNotEmpty(fileData.getFilePath())) {
            logger.warn("Reading data from file id {} failed. File does not exist or is empty.", fileData.getFileId());
            throw new SourceFileIsEmptyException("Reading data from file id " + fileData.getFileId() + " - failed. File does not exist or is empty.");
        }

        try{
            ContentStorage<String> rawContent = new ContentStorage<>(Files.readAllLines(fileData.getFilePath()));
            logger.debug("Reading data from file id {} - success.", fileData.getFileId());
            return rawContent;
        } catch (Throwable e) {
            logger.warn("Reading data from file id {} failed.", fileData.getFileId(), e);
            throw new ReadingContentFromFileException(e, "Reading data from file id " + fileData.getFileId() + " - failed. Problem while reading all lines.");
        }
    }
}
