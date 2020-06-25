package com.justinefactory.writing.writers.file.writers;

import com.justinefactory.domain.PathInfo;
import com.justinefactory.writing.domain.ContentStorage;
import com.justinefactory.writing.domain.JsonStorage;
import com.justinefactory.writing.exceptions.ContentWritingException;
import com.justinefactory.writing.writers.ContentWriter;

public class JsonFileWriter implements ContentWriter<JsonStorage, PathInfo> {

    @Override
    public void writeContent(JsonStorage jsonStorage, PathInfo pathInfo) throws ContentWritingException {
        ContentStorage<String> contentStorage = new ContentStorage<>(jsonStorage.getAllContent());
        PlainFileWriter writer = new PlainFileWriter();
        writer.writeContent(contentStorage, pathInfo);
    }
}
