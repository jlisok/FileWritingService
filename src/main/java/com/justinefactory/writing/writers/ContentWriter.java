package com.justinefactory.writing.writers;

import com.justinefactory.writing.exceptions.ContentWritingException;

import java.io.IOException;
import java.util.Collection;

public interface ContentWriter<T> {

    void writeContent(Collection<T> content) throws IOException, ContentWritingException;

}
