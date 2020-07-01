package com.justinefactory.writing.util;

import com.justinefactory.domain.PathInfo;
import com.justinefactory.writing.exceptions.ContentWritingException;

import java.nio.file.Files;

public class DirectoriesCreator implements StorageContainerCreator<PathInfo> {

    @Override
    public void createNonExistingStorageContainers(PathInfo fileData) throws ContentWritingException {
        try {
            Files.createDirectories(fileData.getPath().getParent());
        } catch (Throwable e) {
            throw new ContentWritingException(e, "Trouble while creating parent directories of file {}." + fileData.getPath());
        }
    }

}
