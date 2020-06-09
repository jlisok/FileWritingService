package com.justinefactory.writing.util;

import com.justinefactory.domain.PathData;
import com.justinefactory.writing.exceptions.ContentWritingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.nio.file.Files;

public class CheckerIfFileAlreadyWritten implements CheckerIfContentAlreadyWritten {

    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public void assureNotExist(PathData fileData) throws ContentWritingException {
        if (Files.exists(fileData.getFilePath())) {
            logger.info("Could not create file {}. File {} already exists.", fileData.getFileId(), fileData.getFilePath());
            throw new ContentWritingException("Could not create file. File already exists.");
        }
    }
}
