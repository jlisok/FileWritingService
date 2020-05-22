package com.justinefactory.writing.service;

import java.util.Collection;

public interface ContentGenerator<T> {

    Collection<T> generateContent(int nLines) throws ContentGeneratingException;

    Collection<T> generateContent() throws ContentGeneratingException;

}
