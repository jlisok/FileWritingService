package com.justinefactory.reading.readers;

import com.justinefactory.reading.exceptions.ContentReadingException;

import java.util.List;

public interface ContentReader<RawContent> {

    List<RawContent> readContent() throws ContentReadingException;

}
