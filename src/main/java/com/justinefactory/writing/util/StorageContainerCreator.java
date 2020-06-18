package com.justinefactory.writing.util;

import com.justinefactory.writing.exceptions.ContentWritingException;

public interface StorageContainerCreator<WritingInfo> {

    void createNonExistingStorageContainers(WritingInfo writingInfo) throws ContentWritingException;

}
