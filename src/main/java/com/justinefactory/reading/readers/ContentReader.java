package com.justinefactory.reading.readers;

import com.justinefactory.reading.exceptions.ContentReadingException;
import com.justinefactory.writing.domain.Content;

public interface ContentReader<ContentType> {

    ContentType readContent() throws ContentReadingException;

}
