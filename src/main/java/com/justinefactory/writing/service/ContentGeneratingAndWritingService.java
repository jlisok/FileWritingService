package com.justinefactory.writing.service;

import com.justinefactory.writing.domain.Content;
import com.justinefactory.writing.exceptions.ContentGeneratingException;
import com.justinefactory.writing.exceptions.ContentWritingException;
import com.justinefactory.writing.generators.ContentGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;


class ContentGeneratingAndWritingService<ContentType, FormattedContent> {

    private final ContentGenerator<ContentType> contentGenerator;
    private final ContentWritingService<Content<ContentType>, Content<FormattedContent>> contentWritingService;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    public ContentGeneratingAndWritingService(ContentWritingService<Content<ContentType>, Content<FormattedContent>> fw, ContentGenerator<ContentType> rig) {
        logger.info("Writing service - initialization.");
        this.contentWritingService = fw;
        this.contentGenerator = rig;
    }


    public void processContent(int nLines) throws ContentWritingException, ContentGeneratingException {
        Content<ContentType> content = contentGenerator.generateContent(nLines);
        contentWritingService.writeContent(content);
        logger.info("Writing service - finished processing successfully.");
    }

}
