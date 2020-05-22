package com.justinefactory.writing.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
            logger.warn("Parsing csvFile content to ThreeElemContent class - CSV content does not match column type requirements of this service.");
            throw new ContentParsingException(e, "CSV content does not match column type requirements of this service.");
        }

    }
}
