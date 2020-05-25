package com.justinefactory.writing.service;

import com.justinefactory.writing.service.exceptions.ContentGeneratingException;

import java.util.Collection;

public interface ContentGenerator<T> {

    Collection<T> generateContent(int nLines) throws ContentGeneratingException;

    Collection<T> generateContent() throws ContentGeneratingException;

}
