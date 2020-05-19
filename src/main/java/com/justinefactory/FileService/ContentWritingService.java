package com.justinefactory.FileService;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;


class ContentWritingService<T extends Serializable> {

    private ContentWriter<T> contentWriter;
    private ContentGenerator<T> contentGenerator;
    private static final Logger logger = LogManager.getLogger(ContentWritingService.class);


    public ContentWritingService(ContentWriter fw, ContentGenerator rig) {
        this.contentWriter = fw;
        this.contentGenerator = rig;
    }


    public void processFile(int nLines) throws IOException, CouldNotWrite2FileAlreadyExists, ContentGeneratingException {
        logger.info("Initializing writing service.");
        Collection<T> content = contentGenerator.generateContent(nLines);
        contentWriter.writeContent(content);
        logger.info("Writing service has finished processing successfully.");

    }

}
