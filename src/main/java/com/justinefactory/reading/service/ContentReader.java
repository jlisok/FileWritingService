package com.justinefactory.reading.service;

import com.justinefactory.reading.service.exceptions.ContentReadingException;

import java.util.List;

interface ContentReader<RawContent> {

    List<RawContent> readContent() throws ContentReadingException;

}
