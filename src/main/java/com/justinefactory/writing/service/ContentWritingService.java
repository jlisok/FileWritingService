package com.justinefactory.writing.service;

import com.justinefactory.writing.generators.ContentGenerator;
import com.justinefactory.writing.exceptions.ContentGeneratingException;
import com.justinefactory.writing.exceptions.ContentWritingException;
import com.justinefactory.writing.writers.ContentWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Collection;


class ContentWritingService<T> {

    private final ContentWriter<T> contentWriter;
    private final ContentGenerator<T> contentGenerator;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());


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
