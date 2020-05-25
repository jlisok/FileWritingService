package com.justinefactory.reading.service;

import com.justinefactory.domain.ThreeElemContent;
import com.justinefactory.reading.service.exceptions.ContentParsingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

class ThreeElementCsvParser implements CsvLineParser<ThreeElemContent> {

    private static final Logger logger = LogManager.getLogger(ThreeElementCsvParser.class);

    @Override
    public ThreeElemContent parseLine(String[] csvLine) throws ContentParsingException {
        logger.debug("Parsing csvFile content to ThreeElemContent class.");
        try {
            Long timeStamp = Long.parseLong(csvLine[0]);
            Integer randomInt = Integer.parseInt(csvLine[1]);
            String randomString = csvLine[2];
            ThreeElemContent threeElemContent = new ThreeElemContent(timeStamp, randomInt, randomString);
            logger.debug("Parsing csvFile content to ThreeElemContent class - success.");
            return threeElemContent;
        } catch (Throwable e) {
            logger.warn("Parsing csvFile content to ThreeElemContent class - CSV line: {} does not match column type requirements. Message: {}", csvLine, e.getMessage());
            throw new ContentParsingException(e, "CSV content - " + Arrays.toString(csvLine) + " - does not match column type requirements.");
        }

    }
}
