package com.justinefactory.reading.readers;

import com.justinefactory.domain.PathInfo;
import com.justinefactory.reading.exceptions.ContentReadingException;
import com.justinefactory.reading.exceptions.ReadingContentFromFileException;
import com.justinefactory.reading.exceptions.SourceFileIsEmptyException;
import com.justinefactory.writing.domain.CsvContent;
import com.opencsv.CSVReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Reader;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;

import static com.justinefactory.util.FileNotEmptyAndExistChecker.checkIfFileExistsIsNotDirAndNotEmpty;

class CsvContentReader implements ContentReader<CsvContent> {

    private final PathInfo fileData;
    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    CsvContentReader(PathInfo fd) {
        fileData = fd;
    }


    @Override
    public CsvContent readContent() throws ContentReadingException {
        logger.debug("Reading data from file id {}", fileData.getId());
        if (!checkIfFileExistsIsNotDirAndNotEmpty(fileData.getPath())) {
            logger.warn("Reading data from file id {} failed. File does not exist or is empty.", fileData.getId());
            throw new SourceFileIsEmptyException("Reading data from file id " + fileData.getId() + " - failed. File does not exist or is empty.");
        }

        try (Reader reader = Files.newBufferedReader(fileData.getPath());
             CSVReader csvReader = new CSVReader(reader)
        ) {
            CsvContent rawContent = new CsvContent(csvReader.readAll());
            logger.debug("Reading data from file id {} - success.", fileData.getId());
            return rawContent;
        } catch (Throwable e) {
            logger.warn("Reading data from file id {} failed.", fileData.getId(), e);
            throw new ReadingContentFromFileException(e, "Reading data from file id " + fileData.getId() + " - failed.");
        }
    }
}

