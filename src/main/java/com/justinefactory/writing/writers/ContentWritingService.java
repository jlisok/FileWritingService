package com.justinefactory.writing.writers;

import com.justinefactory.domain.PathData;
import com.justinefactory.reading.exceptions.ContentStoringException;
import com.justinefactory.writing.converters.ContentConverter;
import com.justinefactory.writing.exceptions.ContentWritingException;
import com.justinefactory.writing.util.CheckerIfContentAlreadyWritten;
import com.justinefactory.writing.util.CheckerIfFileAlreadyWritten;
import com.justinefactory.writing.util.DirectoriesCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class ContentWritingService<Content, ReadyToWriteContent> {

    private final PathData pathData;
    private final CheckerIfContentAlreadyWritten checkerIfContentWritten;
    private final DirectoriesCreator directoriesCreator;
    private final ContentConverter<Content, ReadyToWriteContent> converter;
    private final ContentWriter<ReadyToWriteContent> writer;
    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    ContentWritingService(PathData fd, CheckerIfFileAlreadyWritten ciaw, DirectoriesCreator dc, ContentConverter<Content, ReadyToWriteContent> cr, ContentWriter<ReadyToWriteContent> wt) {
        pathData = fd;
        checkerIfContentWritten = ciaw;
        directoriesCreator = dc;
        converter = cr;
        writer = wt;
    }

    public void writeContent(Content content) throws ContentWritingException, ContentStoringException {
        logger.info("Writing content to path {} - initialization.", pathData.getFilePath());
        if (content == null) {
            throw new ContentWritingException("Problem while Writing content to path " + pathData.getFilePath() + " content " + content + " is empty or does not exist.");
        }
        checkerIfContentWritten.assureNotExist(pathData);
        directoriesCreator.createNonExistingDirectories(pathData);
        logger.debug("Writing content to path {} - all dirs to path have been created.", pathData.getFilePath());
        ReadyToWriteContent readyToWriteContent = converter.convertContent(content);
        logger.debug("Writing content to path {} - content has been converted to lines.", pathData.getFilePath());
        writer.writeContent(readyToWriteContent, pathData);
        logger.debug("Writing content to path {} - lines have been written and appended into path.", pathData.getFilePath());
        logger.info("Writing content to path {} - success.", pathData.getFilePath());
    }


}
