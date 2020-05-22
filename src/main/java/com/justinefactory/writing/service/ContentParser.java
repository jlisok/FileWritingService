package com.justinefactory.writing.service;

interface ContentParser<Line, Content> {
    Content parseLine(Line line) throws ContentParsingException;
}
