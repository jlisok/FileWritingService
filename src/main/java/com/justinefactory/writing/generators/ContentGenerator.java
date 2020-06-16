package com.justinefactory.writing.generators;

import com.justinefactory.reading.exceptions.ContentStoringException;
import com.justinefactory.writing.domain.ContentStorage;
import com.justinefactory.writing.exceptions.ContentGeneratingException;

public interface ContentGenerator<Content> {

    ContentStorage<Content> generateContent(int nLines) throws ContentGeneratingException, ContentStoringException;

}
