package com.justinefactory.writing.converters;

import com.justinefactory.domain.ThreeElemContent;
import com.justinefactory.writing.domain.ContentStorage;
import com.justinefactory.writing.exceptions.ContentConversion2ReadyToWriteException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class ThreeElementContentToCsvLinesConverter implements ContentToCsvLinesConverter<ContentStorage<ThreeElemContent>> {

    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public ContentStorage<String[]> convertContent(ContentStorage<ThreeElemContent> content) throws ContentConversion2ReadyToWriteException {
        checkIfContentEmpty(content);
        ContentStorage<String[]> readyToWriteContent = new ContentStorage<>();
        for (ThreeElemContent item : content.getContent()) {
            readyToWriteContent.addContent(new String[]{item.getTimeStamp().toString(), item.getRandomInt().toString(), item.getRandomString()});
            logger.debug("Converting ThreeElemContent object {} into csvFile lines - success.", content);
        }
        return readyToWriteContent;
    }

    private void checkIfContentEmpty(ContentStorage<ThreeElemContent> content) throws ContentConversion2ReadyToWriteException {
        if (content.getContent().isEmpty()) {
            throw new ContentConversion2ReadyToWriteException("Converting content " + content + " into csvFile lines - failed. Content was empty.");
        }
    }

}
