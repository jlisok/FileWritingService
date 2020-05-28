package com.justinefactory.reading.parsers;

import com.justinefactory.reading.exceptions.ContentParsingException;

public interface ContentParser<Line, Content> {

    public Content parseLine(Line line) throws ContentParsingException;
}
