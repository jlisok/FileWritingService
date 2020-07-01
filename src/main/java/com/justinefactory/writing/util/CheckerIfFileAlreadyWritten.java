package com.justinefactory.writing.util;

import com.justinefactory.domain.PathInfo;
import com.justinefactory.writing.exceptions.ContentWritingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.nio.file.Files;

public class CheckerIfFileAlreadyWritten implements CheckerIfContentAlreadyWritten<PathInfo> {

    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public void assureNotExist(PathInfo fileData) throws ContentWritingException {
        if (Files.exists(fileData.getPath())) {
            logger.info("Could not create file {}. File {} already exists.", fileData.getId(), fileData.getURI());
            throw new ContentWritingException("Could not create file. File already exists.");
        }
    }
}
