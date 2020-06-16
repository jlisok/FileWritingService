package com.justinefactory.writing.writers;

import com.justinefactory.domain.PathData;
import com.justinefactory.writing.exceptions.ContentWritingException;

import java.io.IOException;

public interface ContentWriter<Content> {

    void writeContent(Content content, PathData pathData) throws ContentWritingException;

}
