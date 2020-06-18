package com.justinefactory.util;

import com.justinefactory.reading.exceptions.ContentReadingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileNotEmptyAndExistChecker {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static boolean checkIfFileExistsIsNotDirAndNotEmpty(Path path) throws ContentReadingException {
        try {
            return (Files.exists(path) & Files.size(path) > 0 & !Files.isDirectory(path));
        } catch (Throwable e) {
            logger.warn("Reading data from file id {} failed.", path, e);
            throw new ContentReadingException(e,"Reading data from file id " + path + " - failed.");
        }

    }

}
