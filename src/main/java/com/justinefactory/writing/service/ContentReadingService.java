package com.justinefactory.writing.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ContentReadingService<RawContent, OutContent> {

    private final ContentReader<RawContent> contentReader;
    private final ContentParser<RawContent, OutContent> contentParser;
    private static final Logger logger = LogManager.getLogger(ContentWritingService.class);


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
