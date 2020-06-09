package com.justinefactory.reading.parsers;

import com.justinefactory.domain.ThreeElemContent;
import com.justinefactory.reading.exceptions.ContentParsingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;

public class ThreeElementCsvParser implements CsvLineParser<ThreeElemContent> {

    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public ThreeElemContent parseLine(String[] csvLine) throws ContentParsingException {
        logger.debug("Parsing csvFile line to ThreeElemContent class.");
        if (csvLine.length != 3) {
            logger.warn("Parsing csvFile line to ThreeElemContent class - CSV line: {} does not equal required number of columns (3).", csvLine);
            throw new ContentParsingException("CSV line - " + Arrays.toString(csvLine) + " - does not equal required number of columns (3).");
        }
        try {
            Long timeStamp = Long.parseLong(csvLine[0]);
            Integer randomInt = Integer.parseInt(csvLine[1]);
            String randomString = csvLine[2];
            ThreeElemContent threeElemContent = new ThreeElemContent(timeStamp, randomInt, randomString);
            logger.debug("Parsing csvFile line to ThreeElemContent class - success.");
            return threeElemContent;
        } catch (Throwable e) {
            logger.warn("Parsing csvFile line to ThreeElemContent class - CSV line: {} does not match column type requirements. Message: {}", csvLine, e.getMessage());
            throw new ContentParsingException(e, "CSV content - " + Arrays.toString(csvLine) + " - does not match column type requirements.");
        }

    }
}
