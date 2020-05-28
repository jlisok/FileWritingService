package com.justinefactory.writing.generators;

import com.justinefactory.writing.exceptions.ContentGeneratingException;

import java.util.Collection;

public interface ContentGenerator<T> {

    Collection<T> generateContent(int nLines) throws ContentGeneratingException;

    Collection<T> generateContent() throws ContentGeneratingException;

}
