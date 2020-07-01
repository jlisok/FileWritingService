package com.justinefactory.writing.generators;

import com.justinefactory.writing.domain.Content;
import com.justinefactory.writing.exceptions.ContentGeneratingException;

public interface ContentGenerator<ContentType> {

    Content<ContentType> generateContent(int nLines) throws ContentGeneratingException;

}
