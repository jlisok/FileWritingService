package com.justinefactory.writing.service;

import com.justinefactory.domain.WritingInfo;
import com.justinefactory.writing.converters.ContentConverter;
import com.justinefactory.writing.exceptions.ContentWritingException;
import com.justinefactory.writing.util.CheckerIfContentAlreadyWritten;
import com.justinefactory.writing.util.StorageContainerCreator;
import com.justinefactory.writing.writers.ContentWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class ContentWritingService<ContentType, ReadyToWriteContent> {

    private final WritingInfo writingInfo;
    private final CheckerIfContentAlreadyWritten<WritingInfo> checkerIfContentWritten;
    private final StorageContainerCreator<WritingInfo> storageContainerCreator;
    private final ContentConverter<ContentType, ReadyToWriteContent> converter;
    private final ContentWriter<ReadyToWriteContent, WritingInfo> writer;
    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    ContentWritingService(WritingInfo fd, CheckerIfContentAlreadyWritten<WritingInfo> cicaw, StorageContainerCreator<WritingInfo> scc, ContentConverter<ContentType, ReadyToWriteContent> cr, ContentWriter<ReadyToWriteContent, WritingInfo> wt) {
        writingInfo = fd;
        checkerIfContentWritten = cicaw;
        storageContainerCreator = scc;
        converter = cr;
        writer = wt;
    }

    public void writeContent(ContentType content) throws ContentWritingException {
        logger.info("Writing content to path {} - initialization.", writingInfo.getURI());
        if (content == null) {
            throw new ContentWritingException("Problem while Writing content to path " + writingInfo.getURI() + " content " + content + " is empty or does not exist.");
        }
        checkerIfContentWritten.assureNotExist(writingInfo);
        storageContainerCreator.createNonExistingStorageContainers(writingInfo);
        logger.debug("Writing content to path {} - all dirs to path have been created.", writingInfo.getURI());
        ReadyToWriteContent readyToWriteContent = converter.convertContent(content);
        logger.debug("Writing content to path {} - content has been converted to lines.", writingInfo.getURI());
        writer.writeContent(readyToWriteContent, writingInfo);
        logger.info("Writing content to path {} - success.", writingInfo.getURI());
    }


}
