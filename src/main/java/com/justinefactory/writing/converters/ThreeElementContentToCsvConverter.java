package com.justinefactory.writing.converters;

import com.justinefactory.domain.ThreeElemContent;
import com.justinefactory.writing.exceptions.ContentConvertingToLineException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class ThreeElementContentToCsvConverter implements ContentToCsvLineConverter<ThreeElemContent> {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public String[] varsToCsvLine(ThreeElemContent content) throws ContentConvertingToLineException {
        if (content == null) {
            throw new ContentConvertingToLineException("Converting ThreeElemContent object " + content + " into csvFile line - failed. Content was empty.");
        }
        logger.debug("Converting ThreeElemContent object {} into csvFile line.", content);
        String[] csvLine = {content.getTimeStamp().toString(), content.getRandomInt().toString(), content.getRandomString()};
        logger.debug("Converting ThreeElemContent object {} into csvFile line - success.", content);
        return csvLine;
    }

}
