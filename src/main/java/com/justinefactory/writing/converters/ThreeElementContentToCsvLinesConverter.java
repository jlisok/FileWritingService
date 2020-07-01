package com.justinefactory.writing.converters;

import com.justinefactory.domain.ThreeElemContent;
import com.justinefactory.writing.domain.ContentStorage;
import com.justinefactory.writing.domain.ContentReadyForCsvWriter;
import com.justinefactory.writing.exceptions.ContentConversion2ReadyToWriteException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class ThreeElementContentToCsvLinesConverter implements ContentConverter<ContentStorage<ThreeElemContent>, ContentReadyForCsvWriter> {

    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public ContentReadyForCsvWriter convertContent(ContentStorage<ThreeElemContent> content) throws ContentConversion2ReadyToWriteException {
        checkIfContentEmpty(content);
        ContentReadyForCsvWriter readyToWriteContent = new ContentReadyForCsvWriter();
        for (ThreeElemContent item : content.getContent()) {
            readyToWriteContent.addContent(new String[]{item.getTimeStamp().toString(), item.getRandomInt().toString(), item.getRandomString()});
            logger.debug("Converting ThreeElemContent object {} into csvFile lines - success.", content);
        }
        return readyToWriteContent;
    }

    private void checkIfContentEmpty(ContentStorage<ThreeElemContent> content) throws ContentConversion2ReadyToWriteException {
        if (content.isEmpty()) {
            throw new ContentConversion2ReadyToWriteException("Converting content " + content + " into csvFile lines - failed. Content was empty.");
        }
    }

}
