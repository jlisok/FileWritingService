package com.justinefactory.reading.readers;

import com.justinefactory.reading.exceptions.ContentReadingException;
import com.justinefactory.writing.domain.ContentStorage;

public interface ContentReader<RawContent> {

    ContentStorage<RawContent> readContent() throws ContentReadingException;

}
