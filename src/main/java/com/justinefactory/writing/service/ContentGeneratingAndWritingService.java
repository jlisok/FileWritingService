package com.justinefactory.writing.service;

import com.justinefactory.writing.domain.ContentStorage;
import com.justinefactory.writing.exceptions.ContentGeneratingException;
import com.justinefactory.writing.exceptions.ContentWritingException;
import com.justinefactory.writing.generators.ContentGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;


class ContentGeneratingAndWritingService<Content, FormattedContent> {

    private final ContentGenerator<Content> contentGenerator;
    private final ContentWritingService<ContentStorage<Content>, ContentStorage<FormattedContent>> contentWritingService;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    public ContentGeneratingAndWritingService(ContentWritingService<ContentStorage<Content>, ContentStorage<FormattedContent>> fw, ContentGenerator<Content> rig) {
        logger.info("Writing service - initialization.");
        this.contentWritingService = fw;
        this.contentGenerator = rig;
    }


    public void processContent(int nLines) throws ContentWritingException, ContentGeneratingException {
        ContentStorage<Content> content = contentGenerator.generateContent(nLines);
        contentWritingService.writeContent(content);
        logger.info("Writing service - finished processing successfully.");
    }

}
