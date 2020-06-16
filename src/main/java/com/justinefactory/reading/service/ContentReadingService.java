package com.justinefactory.reading.service;

import com.justinefactory.reading.exceptions.ContentReadingException;
import com.justinefactory.reading.parsers.ContentParser;
import com.justinefactory.reading.readers.ContentReader;
import com.justinefactory.writing.domain.ContentStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class ContentReadingService<RawContent, OutContent> {

    private final ContentReader<RawContent> contentReader;
    private final ContentParser<RawContent, OutContent> contentParser;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    public ContentReadingService(ContentReader<RawContent> cr, ContentParser<RawContent, OutContent> cp) {
        logger.info("Reading service - initialization.");
        contentReader = cr;
        contentParser = cp;
    }


    public ContentStorage<OutContent> processContent() throws ContentReadingException {
        ContentStorage<RawContent> rawContent = contentReader.readContent();
        ContentStorage<OutContent> content = new ContentStorage<>();
        for (int i = 0; i < rawContent.getContentSize(); i++) {
            content.addContent(contentParser.parseLine(rawContent.getContent(i)));
        }
        logger.info("Reading service - finished processing successfully.");
        return content;
    }


}
