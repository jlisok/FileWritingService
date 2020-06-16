package com.justinefactory.reading.parsers;

import com.justinefactory.reading.exceptions.ContentParsingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class IntegerPlainContentParser implements PlainContentParser<Integer> {

    private final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public Integer parseLine(String rawLine) throws ContentParsingException {
        if (rawLine.isEmpty()) {
            logger.warn("Parsing file line to Integer - failed. Line: {} does not equal required number of columns (1).", rawLine);
            throw new ContentParsingException("Parsing file line to Integer - failed. The line " + rawLine + " does not equal required number of columns (1).");
        }
        try {
            Integer integer = Integer.parseInt(rawLine);
            logger.debug("Parsing file line to Integer - success.");
            return integer;
        } catch (Throwable e) {
            logger.warn("Parsing file line to Integer - failed. Line: {} could not be parsed to Integer.", rawLine, e);
            throw new ContentParsingException(e, "Parsing file line to Integer - failed. Line " + rawLine + "could not be parsed to Integer.");
        }
    }


}
