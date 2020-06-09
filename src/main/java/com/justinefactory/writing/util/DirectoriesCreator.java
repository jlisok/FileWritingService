package com.justinefactory.writing.util;

import com.justinefactory.domain.PathData;
import com.justinefactory.writing.exceptions.ContentWritingException;

import java.nio.file.Files;

public class DirectoriesCreator {

    public void createNonExistingDirectories(PathData fileData) throws ContentWritingException {
        try {
            Files.createDirectories(fileData.getFilePath().getParent());
        } catch (Throwable e){
            throw new ContentWritingException(e, "Trouble while creating parent directories of file {}." + fileData.getFilePath());
        }
    }

}
