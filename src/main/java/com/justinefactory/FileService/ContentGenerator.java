package com.justinefactory.FileService;

import java.io.Serializable;
import java.util.Collection;

public interface ContentGenerator<T extends Serializable> {

    Collection<T> generateContent(int nLines) throws ContentGeneratingException;

    Collection<T> generateContent() throws ContentGeneratingException;

}
