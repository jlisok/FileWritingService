package com.justinefactory.reading.service;

import com.justinefactory.reading.parsers.ContentParser;
import com.justinefactory.reading.readers.ContentReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

public class ContentReadingService<RawContent, OutContent> {

    private final ContentReader<RawContent> contentReader;
    private final ContentParser<RawContent, OutContent> contentParser;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    public ContentReadingService(ContentReader<RawContent> cr, ContentParser<RawContent, OutContent> cp) {
        logger.info("Reading service - initialization.");
        contentReader = cr;
        contentParser = cp;
    }


    public List<OutContent> processContent() throws Exception {
        List<RawContent> rawContent = contentReader.readContent();
        List<OutContent> content = new ArrayList<>();
        for (RawContent item : rawContent) {
            content.add(contentParser.parseLine(item));
        }
        logger.info("Reading service - finished processing successfully.");
        return content;
    }


}
