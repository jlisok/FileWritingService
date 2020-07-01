package com.justinefactory.reading.parsers;

import com.justinefactory.reading.exceptions.ContentParsingException;
import com.justinefactory.writing.domain.Content;

public interface ContentParser<RawContent, ContentType> {

    Content<ContentType> parse(RawContent rawContent) throws ContentParsingException;
}
