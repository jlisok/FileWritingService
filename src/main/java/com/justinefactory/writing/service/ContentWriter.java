package com.justinefactory.writing.service;

import com.justinefactory.writing.service.exceptions.ContentWritingException;

import java.io.IOException;
import java.util.Collection;

interface ContentWriter<T> {

    void writeContent(Collection<T> content) throws IOException, ContentWritingException;

}
