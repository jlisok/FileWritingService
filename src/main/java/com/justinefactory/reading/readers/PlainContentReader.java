package com.justinefactory.reading.readers;

import com.justinefactory.domain.PathInfo;
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

    private final PathInfo fileData;
    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    PlainContentReader(PathInfo fd) {
        fileData = fd;
    }


    @Override
    public ContentStorage<String> readContent() throws ContentReadingException {
        logger.debug("Reading data from file id {}", fileData.getId());
        if (!checkIfFileExistsIsNotDirAndNotEmpty(fileData.getPath())) {
            logger.warn("Reading data from file id {} failed. File does not exist or is empty.", fileData.getId());
            throw new SourceFileIsEmptyException("Reading data from file id " + fileData.getId() + " - failed. File does not exist or is empty.");
        }
        try{
            ContentStorage<String> rawContent = new ContentStorage<>(Files.readAllLines(fileData.getPath()));
            logger.debug("Reading data from file id {} - success.", fileData.getId());
            return rawContent;
        } catch (Throwable e) {
            logger.warn("Reading data from file id {} failed.", fileData.getId(), e);
            throw new ReadingContentFromFileException(e, "Reading data from file id " + fileData.getId() + " - failed. Problem while reading all lines.");
        }
    }

}
