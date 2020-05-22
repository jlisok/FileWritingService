package com.justinefactory.writing.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Collection;


class ContentWritingService<T> {

    private final ContentWriter<T> contentWriter;
    private final ContentGenerator<T> contentGenerator;
    private static final Logger logger = LogManager.getLogger(ContentWritingService.class);


    public ContentWritingService(ContentWriter<T> fw, ContentGenerator<T> rig) {
        logger.info("Writing service - initialization.");
        this.contentWriter = fw;
        this.contentGenerator = rig;
    }


    public void processContent(int nLines) throws IOException, ContentWritingException, ContentGeneratingException {
        Collection<T> content = contentGenerator.generateContent(nLines);
        contentWriter.writeContent(content);
        logger.info("Writing service - finished processing successfully.");
    }

}
