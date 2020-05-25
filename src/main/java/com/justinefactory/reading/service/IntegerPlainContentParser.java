package com.justinefactory.reading.service;

import com.justinefactory.reading.service.exceptions.ContentParsingException;

class IntegerPlainContentParser implements PlainContentParser<Integer> {

    @Override
    public Integer parseLine(String rawData) throws ContentParsingException {
        if (rawData.length() > 0) {
            try {
                return Integer.parseInt(rawData);
            } catch (Throwable e) {
                throw new ContentParsingException(e, "Parsing data from file id - failed. String" + rawData + "could not be parsed to Integer.");
            }
        } else {
            throw new ContentParsingException("Parsing data from file id - failed. The line was empty.");
        }
    }



}
