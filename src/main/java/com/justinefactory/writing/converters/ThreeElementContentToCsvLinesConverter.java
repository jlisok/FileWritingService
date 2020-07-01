package com.justinefactory.writing.converters;

import com.justinefactory.domain.ThreeElemContent;
import com.justinefactory.writing.domain.Content;
import com.justinefactory.writing.domain.CsvContent;
import com.justinefactory.writing.exceptions.ContentConversion2ReadyToWriteException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class ThreeElementContentToCsvLinesConverter implements ContentConverter<Content<ThreeElemContent>, CsvContent> {

    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public CsvContent convertContent(Content<ThreeElemContent> content) throws ContentConversion2ReadyToWriteException {
        checkIfContentEmpty(content);
        CsvContent readyToWriteContent = new CsvContent();
        for (ThreeElemContent item : content.getContent()) {
            readyToWriteContent.addContent(new String[]{item.getTimeStamp().toString(), item.getRandomInt().toString(), item.getRandomString()});
            logger.debug("Converting ThreeElemContent object {} into csvFile lines - success.", content);
        }
        return readyToWriteContent;
    }

    private void checkIfContentEmpty(Content<ThreeElemContent> content) throws ContentConversion2ReadyToWriteException {
        if (content.isEmpty()) {
            throw new ContentConversion2ReadyToWriteException("Converting content " + content + " into csvFile lines - failed. Content was empty.");
        }
    }

}
