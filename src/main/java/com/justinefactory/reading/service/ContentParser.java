package com.justinefactory.reading.service;

import com.justinefactory.reading.service.exceptions.ContentParsingException;

interface ContentParser<Line, Content> {

    Content parseLine(Line line) throws ContentParsingException;
}
