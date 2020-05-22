package com.justinefactory.writing.service;

public class IntegerPlainContentParser implements PlainContentParser<Integer> {

    @Override
    public Integer parseLine(String rawData) throws ContentParsingException {
        if (rawData.length() > 0) {
            try {
                return Integer.parseInt(rawData);
            } catch (Throwable e) {
                throw new ContentParsingException(e, "Parsing data from file id - failed. Content format does not match service requirements.");
            }
        } else {
            throw new ContentParsingException("Parsing data from file id - failed. The line was empty.");
        }
    }
}
